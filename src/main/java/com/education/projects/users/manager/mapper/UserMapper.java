package com.education.projects.users.manager.mapper;

import com.education.projects.users.manager.entity.Level.Level;
import com.education.projects.users.manager.entity.Role.Role;
import com.education.projects.users.manager.request.dto.UserDtoReq;
import com.education.projects.users.manager.response.dto.LevelDtoResp;
import com.education.projects.users.manager.response.dto.RoleDtoResp;
import com.education.projects.users.manager.response.dto.UserDtoResp;
import com.education.projects.users.manager.entity.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleDtoResp", source = "roleDtoResp")
    @Mapping(target = "levelDtoResp", source = "levelDtoResp")
    @Mapping(target = "id", expression = "java(user.getId())")
    UserDtoResp userToUserDto(User user, RoleDtoResp roleDtoResp,
                              LevelDtoResp levelDtoResp);

    @Mapping(target = "role", expression = "java(roleMap)")
    @Mapping(target = "level", expression = "java(levelMap)")
    @Mapping(target = "id", expression = "java(null)")
    User userDtoToUser(UserDtoReq userDtoReq, Role roleMap, Level levelMap);

    List<UserDtoResp> userListToUserDtoList(List<User> users);

    default UserDtoResp userListToUserDtoList(User user) {
        UserDtoResp userDtoResp = new UserDtoResp();
        RoleDtoResp roleDtoResp = new RoleDtoResp(user.getRole().getId(),user.getRole().getRoleDescr());
        userDtoResp.setRoleDtoResp(roleDtoResp);
        LevelDtoResp levelDtoResp = new LevelDtoResp(user.getLevel().getId(),user.getLevel().getLevelDescr());
        userDtoResp.setLevelDtoResp(levelDtoResp);
        userDtoResp.setId(user.getId());
        userDtoResp.setFirstName(user.getFirstName());
        userDtoResp.setLastName(user.getLastName());
        userDtoResp.setPassword(user.getPassword());
        userDtoResp.setEmail(user.getEmail());
        userDtoResp.setPhone(user.getPhone());
        userDtoResp.setCreatedAt(user.getCreatedAt());
        userDtoResp.setModificationAt(user.getModificationAt());

        return userDtoResp;
    }
}
