package com.education.projects.users.manager.exception;

import java.util.UUID;

public class LevelNotFoundException extends Exception{
    public LevelNotFoundException (UUID levelId){
        super("The level with id " + levelId + " wasn't found");
    }
}
