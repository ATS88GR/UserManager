package com.education.projects.users.manager.mapper;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDtoResp userToUserDto(User user);
    User userDtoToUser(UserDtoReq userDtoReq);
    List<UserDtoResp> userListToUserDtoList(List<User> users);
}
