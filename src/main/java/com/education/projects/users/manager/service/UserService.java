package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.User;

import java.util.Collection;

/**
 * The interface for service User objects information
 */
public interface UserService {

    UserDtoResp createUser(UserDtoReq userDtoReq) throws Exception;

    UserDtoResp updateUser(UserDtoReq userDtoReq, Integer id) throws Exception;

    Collection<UserDtoResp> getAllUsers() throws Exception;

    UserDtoResp getUserById(Integer id) throws Exception;

    void deleteUserById(Integer id) throws Exception;

    Collection<User> getSortedFilteredUsers(String sortBy, String sortDirection, String filter)
            throws Exception;
}
