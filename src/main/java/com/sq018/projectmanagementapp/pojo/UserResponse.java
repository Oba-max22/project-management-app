package com.sq018.projectmanagementapp.pojo;

import com.sq018.projectmanagementapp.model.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private User user;
    private Boolean isSuccessful;
    private String message;
}
