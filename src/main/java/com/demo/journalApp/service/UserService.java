package com.demo.journalApp.service;

import com.demo.journalApp.entity.User;
import com.demo.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveNewUser(User user){
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<String> defaultRoles = new ArrayList<>();
            defaultRoles.add("USER");
            user.setRoles(defaultRoles);
        }
        if (user.getPassword() != null && !user.getPassword().startsWith("$2")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<String> defaultRoles = new ArrayList<>();
            defaultRoles.add("USER");
            defaultRoles.add("ADMIN");

            user.setRoles(defaultRoles);
        }

        if (user.getPassword() != null && !user.getPassword().startsWith("$2")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);

    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public void deleteByUsername(String userName){
        userRepository.deleteByUserName(userName);;
    }
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
}
