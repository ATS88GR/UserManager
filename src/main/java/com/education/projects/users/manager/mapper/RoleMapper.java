package com.education.projects.users.manager.mapper;

import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDtoResp roleToRoleDto(Role role);
    List<RoleDtoResp> roleListToRoleDtoList(List<Role> roles);
}
