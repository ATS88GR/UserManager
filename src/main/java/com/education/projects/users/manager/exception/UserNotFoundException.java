package com.education.projects.users.manager.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends Exception{
    public UserNotFoundException (UUID userId){
        super("The user with id " + userId + " not found");
    }
}
