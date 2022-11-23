package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Julius, Anton, Carsten
 */

public class TaskRepository {

    private DatabaseConnectionUtility databaseConnection;

    // Constructor
    public TaskRepository() {
        databaseConnection = DatabaseConnectionUtility.getInstance();
    }


    // ------ SETTERS ------ //

    public void createNewTask(String taskTitle, String taskDescription, int taskPrice, int taskTime, String owner, int projectID) {
        String sql = "INSERT INTO task (title, description, price, time, owner, project) VALUES(?,?,?,?,?,?)";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, taskTitle);
            preparedStatement.setString(2, taskDescription);
            preparedStatement.setInt(3, taskPrice);
            preparedStatement.setInt(4, taskTime);
            preparedStatement.setString(5, owner);
            preparedStatement.setInt(6, projectID);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changeTaskFinished(boolean state, int taskID) {
        String sql = "UPDATE task SET is_finished = ? WHERE id = ?";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBoolean(1, state);
            preparedStatement.setInt(2, taskID);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void linkUserAndTask(String taskUsername, int taskID) {
        String sql = "INSERT INTO user_task (username, taskID) VALUES (?,?)";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, taskUsername);
            statement.setInt(2, taskID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskRepository - linkUserAndTask");
        }

    }

    public void deleteTask(int taskID) {
        String sql = "DELETE FROM task where id = ?";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, taskID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskRepository - deleteTask");
        }
    }

    // ------ GETTERS ------ //

    public ResultSet getTaskResultSet(int taskID) {
        String sql = "SELECT * FROM task WHERE id = ?";
        ResultSet resultSet = null;
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getTasksFromProjectResultSet(int projectID) {
        String sql = "SELECT * FROM task where project = ?";

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public ResultSet getTaskIDResultSet(String taskTitle) {
        String sql = "Select id FROM task where title = ?";
        ResultSet resultSet = null;
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, taskTitle);

            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskRepository - getTaskID");
        }
        return resultSet;
    }

    public ResultSet getAllTaskTitlesResultSet() {
        String sql = "SELECT title FROM task";
        ResultSet resultSet = null;

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
