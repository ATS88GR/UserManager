package com.education.projects.users.manager.service;

import com.education.projects.users.manager.entity.Role.RolePage;
import com.education.projects.users.manager.entity.Role.RoleSearchCriteria;
import com.education.projects.users.manager.response.dto.RoleDtoResp;
import com.education.projects.users.manager.entity.Role.Role;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.UUID;

public interface RoleService {
    Collection<RoleDtoResp> getAllRoles() throws Exception;

    RoleDtoResp getRoleDtoById(UUID id) throws Exception;

    Page<RoleDtoResp> getSortFilterPaginRoles(RolePage rolePage,
                                              RoleSearchCriteria roleSearchCriteria) throws Exception;
}
