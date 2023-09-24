package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.request.UserPage;
import com.education.projects.users.manager.dto.request.UserSearchCriteria;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.UUID;

/**
 * The interface for service User objects information
 */
public interface UserService {

    UserDtoResp createUser(UserDtoReq userDtoReq) throws Exception;

    UserDtoResp updateUser(UserDtoReq userDtoReq, UUID id) throws Exception;

    Collection<UserDtoResp> getAllUsers() throws Exception;

    UserDtoResp getUserById(UUID id) throws Exception;

    void deleteUserById(UUID id) throws Exception;

    Page<UserDtoResp> getSortFilterPaginUsers(UserPage userPage,
                                              UserSearchCriteria userSearchCriteria)
            throws Exception;
}
