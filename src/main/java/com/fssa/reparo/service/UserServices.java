package com.fssa.reparo.service;
import com.fssa.reparo.datamapper.UserMapper;
import com.fssa.reparo.dto.user.UserRequestDto;
import com.fssa.reparo.dto.user.UserResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.validation.UserValidation;
import com.fssa.reparo.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private final UserDao userDao=  new UserDao();
    private final UserValidation userValidation=  new UserValidation();



 
    /**
     * Registers a new user by validating the input and inserting the user into the database.
     *
     * @param userRequest The User object to be registered.
     * @throws ServiceException If there is an issue with database access or validation.
     */
    public void registerUser(UserRequestDto userRequest) throws ServiceException {
        UserValidation validate = new UserValidation();
        UserMapper map = new UserMapper();
        try {
           User user = map.mapRequestDtoToUser(userRequest);
           validate.validNewUser(user);
           userDao.insertUser(user);


        }catch (DAOException | ValidationException e){
            throw new ServiceException(e);
        }

       
    }



    /**
     * Logs in a user by validating the credentials.
     *
     * @param num The user's identification number.
     * @param pass The user's password.
     * @return The result of the login attempt:
     *         - Positive user ID if login is successful.
     *         - Negative values for various login failure cases, e.g.:
     *           -1 if validation fails.
     * @throws ServiceException If there is an issue with validation.
     */

    public int loginUser(Long num , String pass) throws  ServiceException{
        UserValidation validate = new UserValidation();
        try {
           return validate.isUser(num,pass);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }


    }

    /**
     * Retrieves a user based on the specified user ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return A User object representing the user with the specified ID.
     * @throws ServiceException If there is an issue with accessing the database.
     */
    public UserResponseDto getUserById(int id) throws  ServiceException{
        UserResponseDto dto ;
        UserMapper map =  new UserMapper();


        try {
            userValidation.validUserId(id); // Checks the id is Present in database
            User user =  userDao.findUserById(id);
            dto = map.mapUserToResponseDto(user);

        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
        return dto;
    }
    /**
     * Retrieves a list of all users stored in the database.
     *
     * @return A List of User objects representing all users in the database.
     * @throws ServiceException If there is an issue with accessing the database.
     */
    public List<UserResponseDto> getAllUsers() throws ServiceException {
       List<UserResponseDto> userInfo = new ArrayList<>();
       UserMapper map = new UserMapper();

        try {
            List<User> users = userDao.getAllUser();
            for(User user : users ){
                UserResponseDto dto = map.mapUserToResponseDto(user);
                userInfo.add(dto);
            }
           return userInfo;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
    /**
     * updates the password of the user with provided phone number.
     *
     * @param number The phone number of the user to be found.
     * @return true if the password is updated else false if it occurs any issues.
     * @throws ServiceException If there are issues while querying the database.
     */
    public boolean updateUserPassword(String newPassword , long number) throws ServiceException{


        try {
            if(userValidation.userCredentialValidateLogin(number, newPassword)) {
                User user = userDao.findUserByNumber(number);
                if (userValidation.validUserId(user.getId())) {
                    return userDao.updateUserPassword(user.getId(), newPassword);
                }
            }


          return false;
        } catch (DAOException | ValidationException | InvalidEntryException e) {
            throw new ServiceException(e);
        }


    }

    /**
     * updates the password of the user with provided phone number.
     *
     * @param id The id of the user to be found.
     * @return true if the password is updated else false if it occurs any issues.
     * @throws ServiceException If there are issues while querying the database.
     */
    public boolean logOutUser(int id) throws ServiceException{
        try {
           if(userValidation.userIsLogin(id)){
               return userDao.updateLoginStatus(id,false);
           }
            return false;
        } catch (ValidationException |DAOException e) {
            // If any exception Occurs throws as Service Exception.
            throw new ServiceException(e);
        }

    }








}
