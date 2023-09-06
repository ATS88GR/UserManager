package com.education.projects.users.manager.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EmptyException extends Exception{
    public EmptyException(){
        super("The list is empty");
    }
}
