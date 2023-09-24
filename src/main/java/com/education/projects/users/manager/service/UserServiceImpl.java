package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.request.UserPage;
import com.education.projects.users.manager.dto.request.UserSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.User;
import com.education.projects.users.manager.exception.UserNotFoundException;
import com.education.projects.users.manager.mapper.UserMapper;
import com.education.projects.users.manager.repository.UserCriteriaRepository;
import com.education.projects.users.manager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

/**
 * The class for service User information in database
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LevelServiceImpl levelServiceImpl;
    @Autowired
    private RoleServiceImpl roleServiceImpl;
    @Autowired
    private UserCriteriaRepository userCriteriaRepository;
    @Autowired
    private UserMapper userMapper;

    /**
     * Creates a new User object information in the database, returns a User object from database by id
     *
     * @param userDtoReq User object to be added to the database
     * @return User object information from database by id
     * @throws Exception
     */
    public UserDtoResp createUser(UserDtoReq userDtoReq) throws Exception {
        User user = userMapper.userDtoToUser(userDtoReq,
                roleServiceImpl.getRoleById(userDtoReq.getRoleId()),
                levelServiceImpl.getLevelById(userDtoReq.getLevelId()));
        return userMapper.userToUserDto(
                userRepository.save(user),
                new RoleDtoResp(user.getRole().getId(), user.getRole().getRoleDescr()),
                new LevelDtoResp(user.getLevel().getId(), user.getLevel().getLevelDescr()));
    }

    /**
     * Updates the User object information by id with update user information
     *
     * @param userDtoReq User object information to update
     * @param id         id of the user object to be updated
     * @return User object information from database by id
     * @throws Exception
     */
    public UserDtoResp updateUser(UserDtoReq userDtoReq, UUID id) throws Exception {
        if (userRepository.existsById(id)) {
            User userToChange = userMapper.userDtoToUser(userDtoReq,
                    roleServiceImpl.getRoleById(userDtoReq.getRoleId()),
                    levelServiceImpl.getLevelById(userDtoReq.getLevelId()));
            userToChange.setId(id);
            userToChange.setCreatedAt(userRepository.getReferenceById(id).getCreatedAt());
            return userMapper.userToUserDto(
                    userRepository.save(userToChange),
                    new RoleDtoResp(userToChange.getRole().getId(), userToChange.getRole().getRoleDescr()),
                    new LevelDtoResp(userToChange.getLevel().getId(), userToChange.getLevel().getLevelDescr()));
        }
        throw new UserNotFoundException(id);
    }

    /**
     * Gets all users objects information from database
     *
     * @return The list of the User objects
     */
    public Collection<UserDtoResp> getAllUsers() {
        return userMapper.userListToUserDtoList(userRepository.findAll());
    }

    /**
     * Gets the User object information from the database by id
     *
     * @param id id of the user object in database
     * @return The User object from database
     */
    public UserDtoResp getUserById(UUID id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getReferenceById(id);
            return userMapper.userToUserDto(user,
                    new RoleDtoResp(user.getRole().getId(), user.getRole().getRoleDescr()),
                    new LevelDtoResp(user.getLevel().getId(), user.getLevel().getLevelDescr()));
        }
        return getAnonymousUser();
    }

    private UserDtoResp getAnonymousUser() {
        Collection<RoleDtoResp> allRoles = roleServiceImpl.getAllRoles();
        RoleDtoResp anonymous = null;
        for (RoleDtoResp roleDtoResp : allRoles) {
            if (roleDtoResp.getRoleDescr().equals("anonymous"))
                anonymous = roleDtoResp;
        }
        UserDtoResp resp = new UserDtoResp();
        resp.setRoleDtoResp(anonymous);
        return resp;
    }

    /**
     * Removes the row with id from database
     *
     * @param id is a row in database
     */
    public void deleteUserById(UUID id) throws Exception {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
        else throw new UserNotFoundException(id);
    }

    /**
     * @param userPage           is a class with pagination settings
     * @param userSearchCriteria is a class with search settings
     * @return List of users with pagination and search settings
     */
    public Page<UserDtoResp> getSortFilterPaginUsers(UserPage userPage,
                                                     UserSearchCriteria userSearchCriteria) {
        return userCriteriaRepository.findAllWithFilters(userPage, userSearchCriteria);
    }
}
