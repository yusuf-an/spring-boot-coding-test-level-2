package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findById(userId);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        Optional<User> existingUserOptional = userRepository.findByUsername(user.getUsername());
        if (existingUserOptional.isPresent()) {
            throw new RuntimeException("user not found for username: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    public User update(User user, String userId) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            throw new RuntimeException("user not found for id: " + userId);
        }

        User existingUser = existingUserOptional.get();
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }


    public void delete(String userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found for id: " + userId));
        userRepository.deleteById(existingUser.getId());
    }
}
