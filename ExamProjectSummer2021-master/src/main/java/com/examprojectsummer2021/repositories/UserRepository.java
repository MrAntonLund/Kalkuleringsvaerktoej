package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Carsten, Julius & Anton
 */

public class UserRepository {

    private DatabaseConnectionUtility databaseConnection;

    // Constructor
    public UserRepository() {
        databaseConnection = DatabaseConnectionUtility.getInstance();
    }

    // ------ SETTERS ------ //

    // ------ GETTERS ------ //

    //---- Returns a specific user from database, based on username ---- \\
    public ResultSet getUserFromDatabase(String username) {
        String sql = "SELECT user.username, user.first_name, user.last_name, user.role, salary FROM user INNER JOIN role_salary ON user.role = role_salary.role WHERE username = ?";
        ResultSet resultSet = null;

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserRepository - getUserFromDatabase");
        }
        return resultSet;
    }


    public ResultSet getUsersFromTask(int taskID) {
        ResultSet resultSet = null;
        String sql = "SELECT user.username, user.first_name, user.last_name, user.role, user_task.taskID, role_salary.salary FROM user INNER JOIN  user_task ON user.username = user_task.username INNER JOIN role_salary ON user.role = role_salary.role WHERE user_task.taskID = ?";

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserRepository - getUsersFromTask");
        }
        return resultSet;
    }


    public ResultSet getUsersFromProject(int projectID) {
        ResultSet resultSet = null;
        String sql = "SELECT user.username, user.first_name, user.last_name, user.role, user_project.projectID, role_salary.salary FROM user INNER JOIN  user_project ON user.username = user_project.username INNER JOIN role_salary ON user.role = role_salary.role WHERE user_project.projectID = ?";

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserRepository - getUsersFromProject");
        }
        return resultSet;
    }


    public ResultSet getAllUsers() {
        ResultSet resultSet = null;
        String sql = "SELECT user.username, user.first_name, user.last_name, user.role, salary FROM user INNER JOIN role_salary ON user.role = role_salary.role";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserRepository - getAllUsers");
        }
        return resultSet;
    }


    //----- Returns the password for the specific user -----\\
    public ResultSet getPasswordForUsername(String username) {
        ResultSet resultSet = null;
        String sql = "SELECT password FROM user WHERE username = ?";

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserRepository - getPasswordForUsername");
        }
        return resultSet;
    }


}

