package com.fssa.reparo.service;
import com.fssa.reparo.dao.UserDao;
import com.fssa.reparo.dto.user.UserRequestDto;
import com.fssa.reparo.dto.user.UserResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {
    private final UserServices userServices =  new UserServices();

    @BeforeAll
    static  void createUser(){
        UserRequestDto request = new UserRequestDto("Razak",9840326001L,"abd123");
        UserServices user  = new UserServices();
        try {
            user.registerUser(request);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void createUserSuccess(){
        UserDao userDao = new UserDao();
        try {
            User us = userDao.findUserByNumber(9840326001L);
            assertEquals("Razak",(us.getName()));


        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }




    @Test
    void createUserTestFail (){
        UserRequestDto use = new UserRequestDto("Abdul",98403265109L,"123456");

            ServiceException exception = assertThrows(ServiceException.class, () -> userServices.registerUser(use));

            assertEquals("com.fssa.reparo.exception.ValidationException: user Credentials is not valid", exception.getMessage());



    }
    @Test
    void loginTestSuccess(){
        try {
            assertEquals(35,userServices.loginUser(9840326515L ,"pas123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginTestFail(){

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.loginUser(9840326512L ,"pas123"));

        assertEquals("com.fssa.reparo.exception.ValidationException: user not present ", exception.getMessage());


    }
    @Test
    void getUserByIdTestSuccess(){

        try {
            UserResponseDto dto = userServices.getUserById(35);
            assertEquals("Razak Test",dto.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestFail(){

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.getUserById(24));

        assertEquals("com.fssa.reparo.exception.ValidationException: user not present", exception.getMessage());


    }

    @Test
    void getAllUsersTestSuccess(){
        try {
            List<UserResponseDto> users = userServices.getAllUsers();
            Assertions.assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void updateUserPassword(){
        try {
          Assertions.assertTrue(userServices.updateUserPassword("pas123",9840326515L));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateUserPasswordFail(){
        try {
            Assertions.assertFalse(userServices.updateUserPassword("chennai",9840326515L));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void logoutSuccessTest(){
        try {
            Assertions.assertTrue(userServices.logOutUser(35));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void logoutFailTest(){

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.logOutUser(20));

        assertEquals("com.fssa.reparo.exception.ValidationException: com.fssa.reparo.exception.ValidationException: user not present", exception.getMessage());

    }



    @AfterAll
    static void deleteTestUser() {
        UserDao dao = new UserDao();
        try {
            Assertions.assertTrue(dao.removeUser(9840326001L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }
}