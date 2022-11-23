package com.examprojectsummer2021.services;

import com.examprojectsummer2021.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Carsten
 */
public class ValidateLoginService {

    UserRepository userRepository = new UserRepository();

    public boolean validateLogin(String username, String password){
        ResultSet resultSet = userRepository.getPasswordForUsername(username);
        String storedPassword = null;
        try {
            while (resultSet.next()){
                storedPassword = resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ValidateLoginService - validateLogin");
        }
        return password.equals(storedPassword);
    }
}
