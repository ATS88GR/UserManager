package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.entity.Role;

import java.util.Collection;

public interface RoleService {
    Collection<RoleDtoResp> getAllRoles() throws Exception;
    RoleDtoResp getRoleDtoById(Integer id) throws Exception;
    Role getRoleById(Integer id) throws Exception;
}
