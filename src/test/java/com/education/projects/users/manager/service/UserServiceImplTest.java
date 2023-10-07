package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.request.UserPage;
import com.education.projects.users.manager.dto.request.UserSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.entity.User;
import com.education.projects.users.manager.exception.UserNotFoundException;
import com.education.projects.users.manager.mapper.UserMapper;
import com.education.projects.users.manager.repository.UserCriteriaRepository;
import com.education.projects.users.manager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    RoleServiceImpl roleService;
    @Mock
    LevelServiceImpl levelService;
    @Mock
    UserCriteriaRepository userCriteriaRepository;
    @InjectMocks
    UserServiceImpl userService;

    private final UUID uuidUserTest = UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4");

    private final UserDtoReq userDtoReqTest = new UserDtoReq(
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            UUID.fromString("83231afa-8702-4a11-b1a5-7aaa90845c86"),
            UUID.fromString("086d792e-7974-4fe4-b2e0-2dba9f79bed8"));

    private final Role roleExp = new Role(
            UUID.fromString("83231afa-8702-4a11-b1a5-7aaa90845c86"),
            "moderator");
    private final Level levelExp = new Level(
            UUID.fromString("2842d7f9-83bd-4df7-bcbe-1d66a69dc5e3"),
            "amateur");
    private final User userExp = new User(
            null,
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            Timestamp.valueOf("2023-09-23 01:54:24"),
            Timestamp.valueOf("2023-09-23 01:54:24"),
            roleExp,
            levelExp);
    private final User userExp2 = new User(
            null,
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            Timestamp.valueOf("2023-09-23 01:54:24"),
            Timestamp.valueOf("2023-09-23 01:54:24"),
            roleExp,
            levelExp);
    private final RoleDtoResp roleDtoRespExp = new RoleDtoResp(
            UUID.fromString("83231afa-8702-4a11-b1a5-7aaa90845c86"),
            "moderator");
    private final LevelDtoResp levelDtoRespExp = new LevelDtoResp(
            UUID.fromString("2842d7f9-83bd-4df7-bcbe-1d66a69dc5e3"),
            "amateur");
    private final UserDtoResp userDtoRespExp = new UserDtoResp(
            UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"),
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            roleDtoRespExp,
            levelDtoRespExp,
            Timestamp.valueOf("2023-09-23 01:54:24"),
            Timestamp.valueOf("2023-09-23 01:54:24"));

    private final UserDtoResp userDtoRespExp2 = new UserDtoResp(
            UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"),
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            roleDtoRespExp,
            levelDtoRespExp,
            Timestamp.valueOf("2023-09-23 01:54:24"),
            Timestamp.valueOf("2023-09-24 00:00:00"));

    private final RoleDtoResp roleDtoResp = new RoleDtoResp(
            UUID.fromString("83231afa-8702-4a11-b1a5-7aaa90845c86"),
            "moderator");
    private final LevelDtoResp levelDtoResp = new LevelDtoResp(
            UUID.fromString("2842d7f9-83bd-4df7-bcbe-1d66a69dc5e3"),
            "amateur");
    private final UserDtoResp userDtoResp = new UserDtoResp(
            UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"),
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            roleDtoResp,
            levelDtoResp,
            Timestamp.valueOf("2023-09-23 01:54:24"),
            Timestamp.valueOf("2023-09-23 01:54:24"));

    @BeforeEach
    public void setUp() {
        userExp.setId(null);
        userExp.setCreatedAt(Timestamp.valueOf("2023-09-23 01:54:24"));
        userExp.setModificationAt(Timestamp.valueOf("2023-09-23 01:54:24"));

        userExp2.setId(UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"));
        userExp2.setCreatedAt(Timestamp.valueOf("2023-09-23 01:54:24"));
        userExp2.setModificationAt(Timestamp.valueOf("2023-09-23 01:54:24"));

        userDtoRespExp.setModificationAt(Timestamp.valueOf("2023-09-23 01:54:24"));
    }

    /**
     * Test for a successful result of creating a new User
     *
     * @throws Exception
     */
    @Test
    void createUser() throws Exception {
        when(roleService.getRoleById(userDtoReqTest.getRoleId())).thenReturn(roleExp);
        when(levelService.getLevelById(userDtoReqTest.getLevelId())).thenReturn(levelExp);
        when(userMapper.userDtoToUser(userDtoReqTest, roleExp, levelExp)).thenReturn(userExp);

        when(userRepository.save(userExp)).thenReturn(userExp2);
        when(userMapper.userToUserDto(userExp2, roleDtoRespExp, levelDtoRespExp)).thenReturn(userDtoRespExp);

        UserDtoResp userDtoResp = userService.createUser(userDtoReqTest);

        UUID uuidUserExp = UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4");

        assertEquals(uuidUserExp, userDtoResp.getId());
        assertEquals("John", userDtoResp.getFirstName());
        assertEquals("Smith", userDtoResp.getLastName());
        assertEquals("Gib5!?jEs#", userDtoResp.getPassword());
        assertEquals("abcdefg@gmail.com", userDtoResp.getEmail());
        assertEquals("+375334455667", userDtoResp.getPhone());
        assertEquals(roleDtoRespExp, userDtoResp.getRoleDtoResp());
        assertEquals(levelDtoRespExp, userDtoResp.getLevelDtoResp());
        assertEquals(Timestamp.valueOf("2023-09-23 01:54:24"), userDtoResp.getCreatedAt());
        assertEquals(Timestamp.valueOf("2023-09-23 01:54:24"), userDtoResp.getModificationAt());
    }

    /**
     * Test for a successful result of updating the User
     *
     * @throws Exception
     */
    @Test
    void updateUser() throws Exception {
        when(userRepository.existsById(uuidUserTest)).thenReturn(true);
        when(roleService.getRoleById(userDtoReqTest.getRoleId())).thenReturn(roleExp);
        when(levelService.getLevelById(userDtoReqTest.getLevelId())).thenReturn(levelExp);
        when(userMapper.userDtoToUser(userDtoReqTest, roleExp, levelExp)).thenReturn(userExp);
        userExp.setId(uuidUserTest);
        when(userRepository.getReferenceById(uuidUserTest)).thenReturn(userExp);

        userExp2.setModificationAt(Timestamp.valueOf("2023-09-24 00:00:00"));
        userDtoRespExp.setModificationAt(Timestamp.valueOf("2023-09-24 00:00:00"));

        when(userRepository.save(userExp)).thenReturn(userExp2);
        when(userMapper.userToUserDto(userExp2, roleDtoRespExp, levelDtoRespExp)).thenReturn(userDtoRespExp);

        UserDtoResp userDtoResp = userService.updateUser(userDtoReqTest, uuidUserTest);

        assertEquals(UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"), userExp2.getId());

        UUID uuidUserExp = UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4");

        assertEquals(uuidUserExp, userDtoResp.getId());
        assertEquals("John", userDtoResp.getFirstName());
        assertEquals("Smith", userDtoResp.getLastName());
        assertEquals("Gib5!?jEs#", userDtoResp.getPassword());
        assertEquals("abcdefg@gmail.com", userDtoResp.getEmail());
        assertEquals("+375334455667", userDtoResp.getPhone());
        assertEquals(roleDtoRespExp, userDtoResp.getRoleDtoResp());
        assertEquals(levelDtoRespExp, userDtoResp.getLevelDtoResp());
        assertEquals(Timestamp.valueOf("2023-09-23 01:54:24"), userDtoResp.getCreatedAt());
        assertEquals(Timestamp.valueOf("2023-09-24 00:00:00"), userDtoResp.getModificationAt());
    }

    /**
     * Test for an unsuccessful result of updating the User
     *
     * @throws Exception
     */
    @Test
    void updateUserWithFail() {
        when(userRepository.existsById(uuidUserTest)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userDtoReqTest, uuidUserTest));
    }

    /**
     * Test for a successful result of getting all Users
     */
    @Test
    void getAllUsers() {
        userExp.setId(UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"));

        userExp2.setId(UUID.fromString("83231afa-8702-4a11-b1a5-7aaa90845c86"));
        userExp2.setModificationAt(Timestamp.valueOf("2023-09-24 00:00:00"));

        List<User> userList = new ArrayList<>();
        userList.add(userExp);
        userList.add(userExp2);

        UserDtoResp userDtoResp2 = new UserDtoResp(
                UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"),
                "John",
                "Smith",
                "Gib5!?jEs#",
                "abcdefg@gmail.com",
                "+375334455667",
                roleDtoResp,
                levelDtoResp,
                Timestamp.valueOf("2023-09-23 01:54:24"),
                Timestamp.valueOf("2023-09-24 00:00:00"));

        List<UserDtoResp> userDtoRespList = new ArrayList<>();
        userDtoRespList.add(userDtoResp);
        userDtoRespList.add(userDtoResp2);

        List<UserDtoResp> userDtoRespExpList = new ArrayList<>();
        userDtoRespExpList.add(userDtoRespExp);
        userDtoRespExpList.add(userDtoRespExp2);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.userListToUserDtoList(userList)).thenReturn(userDtoRespList);

        assertEquals(userDtoRespExpList, userService.getAllUsers());
    }

    /**
     * Test for a successful result of getting the User by Id
     */
    @Test
    void getUserById() {
        RoleDtoResp roleDtoRespTest = new RoleDtoResp(
                UUID.fromString("83231afa-8702-4a11-b1a5-7aaa90845c86"),
                "moderator");
        LevelDtoResp levelDtoRespTest = new LevelDtoResp(
                UUID.fromString("2842d7f9-83bd-4df7-bcbe-1d66a69dc5e3"),
                "amateur");

        userExp.setId(UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4"));

        when(userRepository.existsById(uuidUserTest)).thenReturn(true);
        when(userRepository.getReferenceById(uuidUserTest)).thenReturn(userExp);
        when(userMapper.userToUserDto(userExp, roleDtoRespTest, levelDtoRespTest)).thenReturn(userDtoRespExp);

        assertEquals(userDtoResp, userService.getUserById(uuidUserTest));
    }

    /**
     * Test for an unsuccessful result of getting the User by Id
     */
    @Test
    void getUserByIdOnFail() {
        Collection<RoleDtoResp> roleDtoRespList = new ArrayList<>();

        RoleDtoResp roleDtoResp2 = new RoleDtoResp(
                UUID.fromString("48a2019d-d925-442b-8b8f-930c8f2420d6"),
                "system admin");
        RoleDtoResp roleDtoResp3 = new RoleDtoResp(
                UUID.fromString("f0cb5357-f61d-4639-8f70-8622340158b5"),
                "user");
        RoleDtoResp roleDtoResp4 = new RoleDtoResp(
                UUID.fromString("e8e39c71-ff02-4547-8e82-fc15fe964039"),
                "anonymous");
        roleDtoRespList.add(roleDtoResp);
        roleDtoRespList.add(roleDtoResp2);
        roleDtoRespList.add(roleDtoResp3);
        roleDtoRespList.add(roleDtoResp4);

        RoleDtoResp roleDtoRespExp = new RoleDtoResp(
                UUID.fromString("e8e39c71-ff02-4547-8e82-fc15fe964039"),
                "anonymous");
        UserDtoResp userDtoRespExp = new UserDtoResp();
        userDtoRespExp.setRoleDtoResp(roleDtoRespExp);

        when(userRepository.existsById(uuidUserTest)).thenReturn(false);
        when(roleService.getAllRoles()).thenReturn(roleDtoRespList);
        userDtoRespExp = userService.getUserById(uuidUserTest);

        assertNull(userDtoRespExp.getId());
        assertEquals(roleDtoRespExp, userDtoRespExp.getRoleDtoResp());
    }

    /**
     * Test for a successful result of deleting the User by Id
     *
     * @throws Exception
     */
    @Test
    void deleteUserById() throws Exception {
        when(userRepository.existsById(uuidUserTest)).thenReturn(true);
        userService.deleteUserById(uuidUserTest);

        verify(userRepository, times(1)).deleteById(uuidUserTest);
    }

    /**
     * Test for an unsuccessful result of deleting the User by Id
     */
    @Test
    void deleteUserByIdOnFail() {
        when(userRepository.existsById(uuidUserTest)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUserById(uuidUserTest));
    }

    @Test
    void getSortFilterPaginUsers() {
        Page<UserDtoResp> pageUserDtoRespExp = mock(Page.class);

        UserPage userPage = new UserPage();
        userPage.setPageNumber(0);
        userPage.setSortBy(String.valueOf(Sort.Direction.ASC));
        userPage.setPageSize(5);
        userPage.setSortBy("firstName");

        UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
        userSearchCriteria.setFirstName("John");
        userSearchCriteria.setLastName("Smith");

        when(userCriteriaRepository.findAllWithFilters(
                userPage, userSearchCriteria)).thenReturn(pageUserDtoRespExp);

        assertEquals(pageUserDtoRespExp, userService.getSortFilterPaginUsers(
                userPage, userSearchCriteria));
    }
}