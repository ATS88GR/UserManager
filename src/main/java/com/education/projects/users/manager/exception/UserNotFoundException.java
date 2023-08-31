package com.education.projects.users.manager.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends Exception{
    Integer userId;
    public UserNotFoundException (UUID userId){
        super("The user with id=" + userId + " wasn't found");
    }
}
