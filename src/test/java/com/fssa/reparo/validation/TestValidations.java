package com.fssa.reparo.validation;
import com.fssa.reparo.exception.InvalidEntryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class TestValidations {

    @Test
    void testNameValidation(){
        Validations validate = new Validations();
        String name = "Abdul RazaK";
        try {
            Assertions.assertTrue(validate.stringValidation(name));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void invalidNameTest(){
        Validations validate = new Validations();
        String name2 =  "Abduu1 razak12";
        try {
            Assertions.assertFalse(validate.stringValidation(name2));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }
    @Test
    void testNumberValidation(){
        Validations validate = new Validations();
        Long num =  8124311602L;
        try {
            Assertions.assertTrue(validate.numberValidation(num));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }
    @Test
    void testInvalidNumberValidation(){
        Validations validate = new Validations();
        Long num =  81243116L;
        try {
            Assertions.assertFalse(validate.numberValidation(num));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }
    @Test
    void testPasswordValidation(){
        Validations validate = new Validations();
        String pass = "abd123";
        try {
            Assertions.assertTrue(validate.passWordValidation(pass));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }
    @Test
    void testInvalidPasswordValidation(){
        Validations validate = new Validations();
        String pass = "1234556";
        try {
            Assertions.assertFalse(validate.passWordValidation(pass));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }
    @Test
    void testWorkshopType(){
        Validations validate = new Validations();
        Assertions.assertTrue(validate.workshopType(2));


    }
    @Test
    void testInvalidWorkshopType(){
        Validations validate = new Validations();
        Assertions.assertFalse(validate.workshopType(6));


    }
    @Test
    void testAddress(){
        Validations validate = new Validations();
        String address = "123  Main Street";
        try {
            Assertions.assertTrue(validate.addressValidation(address));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void testInvalidAddress(){
        Validations validate = new Validations();
        String address = "Invalid!@#$";
        try {
            Assertions.assertFalse(validate.addressValidation(address));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void testVehicleNumber(){
        Validations validate = new Validations();
        String num = "KA01AB1234";
        try {
            Assertions.assertTrue(validate.vehicleNumberValidation(num));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void testInvalidVehicleNumber(){
        Validations validate = new Validations();
        String num = "KA1AB1234";
        try {
            Assertions.assertFalse(validate.vehicleNumberValidation(num));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void testValidYear(){
        Validations validate = new Validations();
        int yr = 2023;
        try {
            Assertions.assertTrue(validate.vehicleYearValidation(yr));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }
    @Test
    void testInValidYear(){
        Validations validate = new Validations();
        int yr = 200;
        try {
            Assertions.assertFalse(validate.vehicleYearValidation(yr));

        }catch(InvalidEntryException e){
            e.printStackTrace();
        }

    }



}


