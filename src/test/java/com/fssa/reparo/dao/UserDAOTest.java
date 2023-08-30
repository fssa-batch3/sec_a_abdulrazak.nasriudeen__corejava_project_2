package com.fssa.reparo.dao;

import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

 class UserDAOTest {
    @BeforeAll
    static void testUserInsert(){
        User use = new User();
        use.setName("Abdul");
        use.setNumber(98403265109L);
        use.setPassword("1234");
        UserDAO userTest = new UserDAO();
        try {
            userTest.insertUser(use);
        }catch (DAOException e){
            e.printStackTrace();

        }

    }


    @Test
    void insertUserSuccess(){
        try {
            UserDAO userTest = new UserDAO();
            User us = userTest.findUserByNumber(98403265109L);
            Assertions.assertEquals(98403265109L,us.getNumber());

        }catch (DAOException e){
            e.printStackTrace();

        }
    }
    @Test
    void testUpdatePassword(){
        try {
            UserDAO userTest = new UserDAO();

           Assertions.assertTrue(userTest.updateUserPassword(9840326,"test"));
            User us = userTest.findUserByNumber(98403265109L);
            Assertions.assertEquals("test",us.getPassword());


        }catch (DAOException e){
            e.printStackTrace();

        }
          }
    @Test
    void deleteUser()
    {
        try {
            UserDAO userTest = new UserDAO();

            Assertions.assertTrue(userTest.removeUser(98403265109L));

        }catch (DAOException e){
            e.printStackTrace();

        }
    }
    @Test
    void getAllUserTest(){

        try {
            UserDAO userTest = new UserDAO();
            List<User> users = userTest.getAllUser();
            Assertions.assertFalse(users.isEmpty());
        } catch (DAOException e) {
           e.printStackTrace();
        }
    }

}
