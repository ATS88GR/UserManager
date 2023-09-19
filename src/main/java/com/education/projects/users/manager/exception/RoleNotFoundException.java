package com.education.projects.users.manager.exception;

import java.util.UUID;

public class RoleNotFoundException extends Exception{
    public RoleNotFoundException (UUID roleId){
        super("The role with id " + roleId + " not found");
    }
}
