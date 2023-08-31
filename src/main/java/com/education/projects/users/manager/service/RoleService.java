package com.education.projects.users.manager.service;

import com.education.projects.users.manager.response.dto.RoleDtoResp;
import com.education.projects.users.manager.entity.Role;

import java.util.Collection;
import java.util.UUID;

public interface RoleService {
    Collection<RoleDtoResp> getAllRoles() throws Exception;
    RoleDtoResp getRoleDtoById(UUID id) throws Exception;
    Role getRoleById(Integer id) throws Exception;
}
