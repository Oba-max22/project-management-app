package com.sq018.projectmanagementapp.pojo;

import lombok.Data;

@Data
public class GenericResponse<T> {

    private T data;
    private Boolean isSuccessful;
    private String message;
}
