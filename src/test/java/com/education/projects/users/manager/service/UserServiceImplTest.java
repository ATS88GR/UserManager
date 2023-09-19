package com.education.projects.users.manager.service;

import com.education.projects.users.manager.mapper.UserMapper;
import com.education.projects.users.manager.repository.UserRepository;
import com.education.projects.users.manager.request.dto.UserDtoReq;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    void createUser() {
        UserDtoReq userDtoReqTest = new UserDtoReq();
    }

    @Test
    void updateUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getSortFilterPaginUsers() {
    }
}