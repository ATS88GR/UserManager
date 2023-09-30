package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.RolePage;
import com.education.projects.users.manager.dto.request.RoleSearchCriteria;
import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.exception.RoleNotFoundException;
import com.education.projects.users.manager.mapper.RoleMapper;
import com.education.projects.users.manager.repository.RoleCriteriaRepository;
import com.education.projects.users.manager.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleMapper roleMapper;
    @Mock
    private RoleCriteriaRepository roleCriteriaRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    /**
     * Tests for a successful result of getting all Roles by id
     */
    @Test
    void getAllRoles() {
        List<RoleDtoResp> roleRespExp = new ArrayList<>();
        roleRespExp.add(
                new RoleDtoResp(UUID.fromString("0994da2b-8439-4843-80e6-69ee7489a3b7"),
                        "user"));
        roleRespExp.add(
                new RoleDtoResp(UUID.fromString("7287e720-6507-4349-893f-42b41ac5b4ac"),
                        "system admin"));

        List<RoleDtoResp> roleResp = new ArrayList<>();
        roleResp.add(
                new RoleDtoResp(UUID.fromString("0994da2b-8439-4843-80e6-69ee7489a3b7"),
                        "user"));
        roleResp.add(
                new RoleDtoResp(UUID.fromString("7287e720-6507-4349-893f-42b41ac5b4ac"),
                        "system admin"));

        List<Role> roleList = new ArrayList<>();
        roleList.add(
                new Role(UUID.fromString("0994da2b-8439-4843-80e6-69ee7489a3b7"),
                        "user"));
        roleList.add(
                new Role(UUID.fromString("7287e720-6507-4349-893f-42b41ac5b4ac"),
                        "system admin"));

        when(roleRepository.findAll()).thenReturn(roleList);
        when(roleMapper.roleListToRoleDtoList(roleList)).thenReturn(roleResp);

        assertEquals(roleRespExp, roleService.getAllRoles());
    }

    /**
     * Tests for a successful result of getting a Role dto by id
     *
     * @throws Exception
     */
    @Test
    void getRoleDtoById() throws Exception {
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        RoleDtoResp roleDtoRespExp = new RoleDtoResp();
        roleDtoRespExp.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        roleDtoRespExp.setRoleDescr("moderator");

        Role role = new Role();
        role.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        role.setRoleDescr("moderator");

        UUID expectedUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        String expectedRoleDescr = "moderator";

        when(roleRepository.existsById(testUUID)).thenReturn(true);
        when(roleRepository.getReferenceById(testUUID)).thenReturn(role);
        when(roleMapper.roleToRoleDto(role)).thenReturn(roleDtoRespExp);
        RoleDtoResp testResult = roleService.getRoleDtoById(testUUID);

        verify(roleRepository).existsById(testUUID);
        verify(roleRepository).getReferenceById(testUUID);
        verify(roleMapper).roleToRoleDto(any());

        assertEquals(expectedUUID, testResult.getId());
        assertEquals(expectedRoleDescr, testResult.getRoleDescr());
    }

    /**
     * Tests for an unsuccessful result of getting a Role dto by id
     */
    @Test
    void getRoleDtoByIdOnFail() {
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        when(roleRepository.existsById(testUUID)).thenReturn(false);

        assertThrows(RoleNotFoundException.class, () -> roleService.getRoleDtoById(testUUID));
    }

    /**
     * Tests for a successful result of getting a Role by id
     *
     * @throws Exception
     */
    @Test
    void getRoleById() throws Exception {
        UUID testUUID = UUID.fromString("346a1793-a65d-487a-b602-4044894682f3");

        Role role = new Role();
        role.setId(UUID.fromString("346a1793-a65d-487a-b602-4044894682f3"));
        role.setRoleDescr("moderator");

        UUID expectedUUID = UUID.fromString("346a1793-a65d-487a-b602-4044894682f3");
        String expectedRole = "moderator";

        when(roleRepository.existsById(testUUID)).thenReturn(true);
        when(roleRepository.getReferenceById(testUUID)).thenReturn(role);
        Role testResult = roleService.getRoleById(testUUID);

        assertEquals(expectedUUID, testResult.getId());
        assertEquals(expectedRole, testResult.getRoleDescr());
    }

    /**
     * Tests for an unsuccessful result of getting a Role by id
     *
     * @throws Exception
     */
    @Test
    void getRoleByIdOnFail() {
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        when(roleRepository.existsById(testUUID)).thenReturn(false);

        assertThrows(RoleNotFoundException.class, () -> roleService.getRoleById(testUUID));
    }

    @Test
    void getSortFilterPaginRoles() {
        Page<RoleDtoResp> pageRoleDtoRespExp = mock(Page.class);
        RolePage rolePage = new RolePage();
        RoleSearchCriteria roleSearchCriteria = new RoleSearchCriteria();
        roleSearchCriteria.setRoleDescr("moderator");

        when(roleCriteriaRepository.findAllWithFilters(
                rolePage, roleSearchCriteria)).thenReturn(pageRoleDtoRespExp);

        assertEquals(pageRoleDtoRespExp, roleService.getSortFilterPaginRoles(
                rolePage, roleSearchCriteria));
    }
}