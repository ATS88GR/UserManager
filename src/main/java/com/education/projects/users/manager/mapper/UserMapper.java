package com.education.projects.users.manager.mapper;

import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.request.dto.UserDtoReq;
import com.education.projects.users.manager.response.dto.UserDtoResp;
import com.education.projects.users.manager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDtoResp userToUserDto(User user);
    @Mapping(target = "role", expression = "java(roleMap)")
    @Mapping(target = "level", expression = "java(levelMap)")
    @Mapping(target = "id", expression = "java(null)")
    User userDtoToUser(UserDtoReq userDtoReq, Role roleMap, Level levelMap);
    List<UserDtoResp> userListToUserDtoList(List<User> users);
}
