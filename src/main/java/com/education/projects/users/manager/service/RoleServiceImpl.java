package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.RolePage;
import com.education.projects.users.manager.dto.request.RoleSearchCriteria;
import com.education.projects.users.manager.exception.EmptyException;
import com.education.projects.users.manager.exception.RoleNotFoundException;
import com.education.projects.users.manager.repository.RoleCriteriaRepository;
import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.mapper.RoleMapper;
import com.education.projects.users.manager.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    RoleCriteriaRepository roleCriteriaRepository;

    /**
     * Gets all roles objects information from database
     *
     * @return The list of the Role objects
     */
    public Collection<RoleDtoResp> getAllRoles() throws Exception {
        Collection<RoleDtoResp> roleDtoResp =
                roleMapper.roleListToRoleDtoList(roleRepository.findAll());
        if (roleDtoResp.isEmpty()) throw new EmptyException();
        return roleDtoResp;
    }

    /**
     * Gets the Role DTO object information from the database by id
     *
     * @param id id of the role object in database
     * @return The Role DTO object from database
     * @throws RoleNotFoundException
     */
    public RoleDtoResp getRoleDtoById(UUID id) throws Exception {
        if (roleRepository.existsById(id))
            return roleMapper.roleToRoleDto(roleRepository.getReferenceById(id));
        throw new RoleNotFoundException(id);
    }

    /**
     * Gets the Role DTO object information from the database by id
     *
     * @param id id of the role object in database
     * @return The Role object from database
     * @throws RoleNotFoundException
     */
    Role getRoleById(UUID id) throws Exception {
        if (roleRepository.existsById(id))
            return roleRepository.getReferenceById(id);
        throw new RoleNotFoundException(id);
    }

    /**
     * Gets paginated list of Role DTO objects from the database
     *
     * @param rolePage           Role page parameters
     * @param roleSearchCriteria criteria of role search
     * @return sorted filtered List of Role DTO objects with pagination
     */
    public Page<RoleDtoResp> getSortFilterPaginRoles(RolePage rolePage,
                                                     RoleSearchCriteria roleSearchCriteria)
            throws Exception {
        Page<RoleDtoResp> roleDtoResp =
                roleCriteriaRepository.findAllWithFilters(rolePage, roleSearchCriteria);
        if (roleDtoResp.isEmpty()) throw new EmptyException();
        return roleDtoResp;
    }
}
