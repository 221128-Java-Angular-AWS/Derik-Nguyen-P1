package com.revature.persistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.UsernameTaken;
import com.revature.pojos.User;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UserNotFoundException;


public class UserDao {
    private Connection connection;

    public UserDao(){
        this.connection = ConnectionManager.getConnection();
    }

    public void create(User user) throws UsernameTaken {
        String sql = "INSERT INTO users (first_name, last_name, username, \"password\", is_manager) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.getIsManager());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new UsernameTaken("Username already taken!");
        }
    }

    public User authenticate(String username, String password) throws UserNotFoundException, IncorrectPasswordException{
        String sql = "SELECT * FROM users WHERE username = ?;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()){
                throw new UserNotFoundException("This username does not exist in our database");
            }
            User user = new User(rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBoolean("is_manager"));
            if (user.getPassword().equals(password)){
                return user;
            }
            throw new IncorrectPasswordException("The password is not correct.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void update(User user) throws UsernameTaken {
        String sql = "UPDATE USERS SET first_name = ?, last_name = ?, username = ?, \"password\" = ?, is_manager = ? WHERE user_id = ?;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.getIsManager());
            pstmt.setInt(6, user.getUserId());
            pstmt.executeUpdate();


        }catch (SQLException e){
            throw new UsernameTaken("Username already taken!");
        }
    }

    public User getUser(Integer user_id) {
        User user = new User();

        try {
            String sql = "SELECT * FROM users WHERE user_id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIsManager(rs.getBoolean("is_manager"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }


    public Set<User> getAllUsers(){
        String sql = "SELECT * FROM users";
        Set<User> setUsers = new HashSet<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                User user = new User(rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("is_manager"));
                setUsers.add(user);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return setUsers;
    }


    public void delete(User user){
        String sql = "DELETE FROM users WHERE USER_ID = ?;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
