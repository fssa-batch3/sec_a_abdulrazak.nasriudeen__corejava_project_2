package dao;

import exception.DaoException;
import model.Booking;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BookingDaoTest {
   @Test
     void insertBookingTest(){
        Booking book  = new Booking();

        book.setVehicleId(1);
        book.setAddress("123123");
        book.setCity("chennai");
        book.setState("TamilNadu");
        book.setProblem("punChre");


        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.insertBooking(book));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void updateRequestStsTest(){
        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.updateRequestSts(1,true));
            Booking book1 = bookDao.getBookingsByVehicleId(1);
            Assertions.assertTrue(book1.isRequestStatus());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateAcceptStatus(){
        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.updateAcceptSts(1,true));
            Booking book = bookDao.getBookingsByVehicleId(1);
            Assertions.assertTrue(book.isAcceptStatus());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getWorkshopByAreaTest(){
        try {
            BookingDao bookDao = new BookingDao();
            ArrayList<Integer> arr = bookDao.findWorkshopByArea("chennai");
            Assertions.assertEquals(14,arr.get(0));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }


    @AfterAll
   static void removeBooking(){
        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.removeBooking(1));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }



}
