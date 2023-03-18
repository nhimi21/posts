package com.betaplan.himi.posts.services;

import com.betaplan.himi.posts.models.User;
import com.betaplan.himi.posts.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByFullName(String fullName){
        return userRepository.findByFullName(fullName);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // authenticate user
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user != null)
            return BCrypt.checkpw(password, user.getPassword());
        else
            return false;
    }

}
