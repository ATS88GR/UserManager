package com.education.projects.users.manager.mapper;

import com.education.projects.users.manager.entity.Level.Level;
import com.education.projects.users.manager.entity.Role.Role;
import com.education.projects.users.manager.request.dto.UserDtoReq;
import com.education.projects.users.manager.response.dto.UserDtoResp;
import com.education.projects.users.manager.entity.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
