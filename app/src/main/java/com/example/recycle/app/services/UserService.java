package com.example.recycle.app.services;

import com.example.recycle.app.models.exceptions.WasteWithThisTypeDoesntExistException;
import jakarta.transaction.Transactional;
import com.example.recycle.app.models.User;
import com.example.recycle.app.models.Waste;
import com.example.recycle.app.models.exceptions.UserNotFoundException;
import com.example.recycle.app.models.exceptions.WasteAlreadyHasAnUserException;
import com.example.recycle.app.models.exceptions.WasteIsNotAssignedToThisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.recycle.app.repositories.UserRepository;

import java.io.Console;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WasteService wasteService;

    @Autowired
    public UserService(UserRepository userRepository, WasteService wasteService) {
        this.userRepository = userRepository;
        this.wasteService = wasteService;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Long id){
       return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User deleteUser(Long id){
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }

    @Transactional
    public User editUser(Long id, User user){
        User editUser = getUser(id);
        editUser.setUsername(user.getUsername());
        editUser.setPassword(user.getPassword());
        return editUser;
    }

    @Transactional
    public User addWasteToUser(Long userId, Long wasteId){
        User user = getUser(userId);
        Waste waste = wasteService.getWaste(wasteId);
        if(Objects.nonNull(waste.getUser()))
            throw new WasteAlreadyHasAnUserException(userId, wasteId);
        user.addWaste(waste);
        waste.setUser(user);
        return user;

    }

    @Transactional
    public User deleteWasteFromUser(Long userId, Long wasteId){
        User user = getUser(userId);
        Waste waste = wasteService.getWaste(wasteId);
        if(!Objects.equals(userId, waste.getUser().getId()))
            throw new WasteIsNotAssignedToThisUser(userId, wasteId);
        user.removeWaste(waste);
        return user;
    }

    @Transactional
    public User addWasteQuantityToUser(Long userId, String type, Long quantity) {
        User user = getUser(userId);
        Waste waste = user.getWasteList().stream().filter(w -> Objects.equals(w.getType(), type)).
                findFirst().orElseThrow(() -> new WasteWithThisTypeDoesntExistException(type));

        waste.setQuantity(waste.getQuantity() + quantity);
        long aux = 0;
        long _quantity = waste.getQuantity();
        long border = waste.getBorder();

        if (_quantity % border == 0)
            waste.setQuantityUntilBonus(0L);

        if (_quantity < border)
            waste.setQuantityUntilBonus(border - _quantity);

        if (_quantity > border) {
            waste.setBorder(border * (_quantity / border + 1));
            waste.setQuantityUntilBonus(waste.getBorder() - _quantity);
            waste.setBorder(border);

        }

        return user;
    }

}
