package com.sq018.projectmanagementapp.service;

import com.sq018.projectmanagementapp.model.User;
import com.sq018.projectmanagementapp.pojo.UserResponse;
import com.sq018.projectmanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createNewUser(User user) {
        UserResponse response = new UserResponse();

        try {

            User savedUser = userRepository.save(user);

            response.setUser(savedUser);
            response.setIsSuccessful(true);
            response.setMessage("User created successfully!");
        } catch (Exception ex) {
            response.setIsSuccessful(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}
