package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.WorkShop;
import com.fssa.reparo.service.WorkShopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class WorkShopServiceTest {
    @Test
    void registerWorkShopTestFail(){
        WorkShopService dao = new WorkShopService();
        WorkShop use =  new WorkShop() ;
        use.setName("razak Test");
        use.setNumber(9840326580L);
        use.setPassword("abd234");
        use.setType(3);
        use.setState("tamil Nadu");
        use.setCity("chennai");
        use.setAddress("123  Main Street");
        try {
            dao.registerWorkShop(use);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginWorkshopTest(){
        WorkShopService serv = new WorkShopService();
        try{
            Assertions.assertEquals(17,serv.loginWorkShop(9840326580L,"abd234"));
        }catch (ServiceException e){
            throw new RuntimeException(e);

        }

    }
    @Test
    void getWorkShopByAreaTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            Assertions.assertFalse(ser.getWorkShopByArea("chennai").isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getWorkShopByTypeTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            Assertions.assertFalse(ser.getWorkShopByType(3).isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getAllWorkShopTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            Assertions.assertFalse(ser.getAllWorkShop().isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getWorkShopByIdTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            WorkShop work = ser.getWorkShopById(17);
            Assertions.assertEquals("razak Test", work.getName() );
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}