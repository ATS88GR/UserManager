package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.mapper.RoleMapper;
import com.education.projects.users.manager.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@Slf4j
public class RoleServiceImpl {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * Gets all roles objects information from database
     *
     * @return The list of the Role objects
     */
    public Collection<RoleDtoResp> getAllRoles() throws Exception{
        try {
            return roleMapper.roleListToRoleDtoList(roleRepository.findAll());
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Gets the Role object information from the database by id
     *
     * @param id id of the role object in database
     * @return The Role object from database
     * @throws Exception
     */
    public RoleDtoResp getRoleDtoById(Integer id) throws Exception {
        try {
            if (roleRepository.existsById(id))
                return roleMapper.roleToRoleDto(roleRepository.getReferenceById(id));
            else {
                Exception e = new Exception("The role wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public Role getRoleById(Integer id) throws Exception {
        try {
            if (roleRepository.existsById(id))
                return roleRepository.getReferenceById(id);
            else {
                Exception e = new Exception("The role wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
