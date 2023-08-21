package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.User;
import com.education.projects.users.manager.entity.UserPage;
import com.education.projects.users.manager.entity.UserSearchCriteria;
import com.education.projects.users.manager.mapper.UserMapper;
import com.education.projects.users.manager.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * The class for service User information in database
 */
@Service
@Slf4j
public class DBUserServiceImpl implements UserService {

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
        try {
            User user = userMapper.userDtoToUser(userDtoReq);
            user.setLevel(levelServiceImpl.getLevelById(userDtoReq.getLevelId()));
            user.setRole(roleServiceImpl.getRoleById(userDtoReq.getRoleId()));

            UserDtoResp userDtoResp = userMapper.userToUserDto(
                    userRepository.save(user));
            userDtoResp.setLevelId(user.getLevel().getId());
            userDtoResp.setRoleId(user.getRole().getId());
            return userDtoResp;
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Updates the User object information by id with update user information
     *
     * @param userDtoReq User object information to update
     * @param id  id of the user object to be updated
     * @return User object information from database by id
     * @throws Exception
     */
    public UserDtoResp updateUser(UserDtoReq userDtoReq, Integer id) throws Exception {
        try {
            if (userRepository.existsById(id)) {
                User userToChange = userMapper.userDtoToUser(userDtoReq);
                userToChange.setId(id);
                return userMapper.userToUserDto(userRepository.save(userToChange));
            } else {
                Exception e = new Exception("The user wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception ex){
            log.error("Error: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Gets all users objects information from database
     *
     * @return The list of the User objects
     */
    public Collection<UserDtoResp> getAllUsers() throws Exception{
        try {
            return userMapper.userListToUserDtoList(userRepository.findAll());
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Gets the User object information from the database by id
     *
     * @param id id of the user object in database
     * @return The User object from database
     * @throws Exception
     */
    public UserDtoResp getUserById(Integer id) throws Exception {
        try {
            if (userRepository.existsById(id))
                return userMapper.userToUserDto(userRepository.getReferenceById(id));
            else {
                Exception e = new Exception("The user wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Removes the row with id from database
     *
     * @param id is a row in database
     */
    public void deleteUserById(Integer id) throws Exception {
        try {
            if (userRepository.existsById(id))
                userRepository.deleteById(id);
            else {
                Exception e = new Exception("The user wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Sorts and filters Users objects information from database, returns list of User objects
     *
     * @param sortBy        Sets the sort order
     * @param sortDirection Sets the sort direction (ACK/DESC)
     * @param filter        The filter parameter, which need to parse
     * @return The list of the User objects
     * @throws Exception
     */
    public Collection<User> getSortedFilteredUsers(String sortBy, String sortDirection, String filter)
            throws Exception {

        String[] arrFilter = filter.split("\\.");
        String key = arrFilter[1];
        String value = arrFilter[2];
        String operation = (arrFilter[0].equals("eq")) ? "= " : "!= ";

        UserSpecification spec = new UserSpecification(new SearchCriteria(key, operation, value));
        return userRepository.findAll(spec);
    }

    public Page<UserDtoResp> getSortedFilteredUsersCommon(UserPage userPage,
                                  UserSearchCriteria userSearchCriteria)
    throws Exception{
        try {
            return userCriteriaRepository.findAllWithFilters(userPage, userSearchCriteria);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
