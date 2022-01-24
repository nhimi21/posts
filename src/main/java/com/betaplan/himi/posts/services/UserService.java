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

    // find user by email
    public User findByEmail(String email) {
        return  this.userRepository.findByEmail(email);
    }
    //find user by fullName
    public User findByFullName(String fullName){
        return this.userRepository.findByFullName(fullName);
    }
    // find user by id
    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            return BCrypt.checkpw(password, user.getPassword());
        }
    }
}
