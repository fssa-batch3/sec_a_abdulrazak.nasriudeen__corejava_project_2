package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User assignUser(ResultSet rs) throws DAOException{
        User user  = new User();
        try {
                user.setName(rs.getString("name"));
                user.setNumber(Long.parseLong(rs.getString("number")));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getBoolean("is_login"));


            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    public boolean insertUser(User use) throws DAOException {
        // This method is used to create user data in db table
        String  query = "insert into user (name,number,password) values (?,?,?)";

        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query)){

            pre.setString(1, use.getName());
            String num =  Long.toString(use.getNumber());

            pre.setString(2,num);

            pre.setString(3,use.getPassword());

            return (pre.executeUpdate()==1);
        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }

    public  boolean updateUserPassword(int  id , String password)throws DAOException {

        String query = "update user set password = ? where id = ?";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre = connect.prepareStatement(query)){

            pre.setString(1,password);

            pre.setInt(2,id);
            return pre.executeUpdate()==1;
        }catch(SQLException | DTBException e){
            throw new DAOException(e);
        }
    }
    public  boolean removeUser(long number)throws DAOException {
        String query = "delete from user where number = ? ;";

        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query)){

            String num = Long.toString(number);
            pre.setString(1,num);
            return pre.executeUpdate() == 1;

        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }


    }
    public  User findUserById(int id) throws DAOException {
        User use =  new User();
        String query =  "Select * from user where id = ?";


        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement prep =  connect.prepareStatement(query)){


            prep.setInt(1,id);
            ResultSet   rs = prep.executeQuery();
            if(rs.next()){
                use = assignUser(rs);
            }

            rs.close();

        } catch (SQLException | DTBException e){
            throw new DAOException(e);
        }
        return use;

    }
    public  User findUserByNumber(long num) throws DAOException {
        User use =  new User();

        String query =  "Select * from user where number = ?";
        String number = Long.toString(num);
        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement prep =  connect.prepareStatement(query)){


            prep.setString(1,number);
            ResultSet   rs = prep.executeQuery();
            while (rs.next()){
                 use = assignUser(rs);
            }

            rs.close();
      

            return use;

        } catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public List<User> getAllUser() throws DAOException {

        String query = "select * from user";
        List<User> users = new ArrayList<>();
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement prep =  connect.prepareStatement(query)
        ){
            ResultSet  rs = prep.executeQuery();



            while(rs.next()){
                User work = assignUser(rs);
                users.add(work);
            }
            rs.close();



        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }
        return users;

    }
    public boolean updateLoginStatus(int id, boolean status) throws DAOException {
        String query = "update user set is_login = ? where id = ?";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre = connect.prepareStatement(query)){

            pre.setBoolean(1,status);
            pre.setInt(2,id);
            return pre.executeUpdate()==1;
        }catch(SQLException | DTBException e){
            throw new DAOException(e);
        }
    }


}

