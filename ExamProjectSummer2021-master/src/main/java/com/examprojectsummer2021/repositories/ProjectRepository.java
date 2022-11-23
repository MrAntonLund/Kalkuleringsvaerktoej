package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.*;


/**
 * @author Julius, Anton, Carsten
 */
public class ProjectRepository {

    private DatabaseConnectionUtility databaseConnection;

    // Constructor
    public ProjectRepository() {
        databaseConnection = DatabaseConnectionUtility.getInstance();
    }

    // ------ SETTERS ------ //

    public void createNewProject(String projectTitle, String projectDescription, String owner,
                                 String inceptionDate, String projectDeadline) {
        String sql = "INSERT INTO project (title, description, owner, inception_date, deadline) VALUES(?,?,?,?,?)";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, projectTitle);
            preparedStatement.setString(2, projectDescription);
            preparedStatement.setString(3, owner);
            preparedStatement.setString(4, inceptionDate);
            preparedStatement.setString(5, projectDeadline);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void linkUserAndProject(String projectUsername, int projectID) {
        String sql = "INSERT INTO user_project (username, projectID) VALUES (?,?)";
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, projectUsername);
            statement.setInt(2, projectID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("projectRepository - linkUserAndProject");
        }
    }

    public void changeProjectFinished(boolean state, int taskID) {
        String sql = "UPDATE project SET is_finished = ? WHERE id = ?";
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

    public void deleteProject(String projectTitle) {
        String sql = "DELETE FROM project where title = ?";

        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, projectTitle);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Project Repository - deleteProject");
        }
    }

    // ------ GETTERS ------ //

    public ResultSet getProjectIDResultSet(String projectTitle) {
        String sql = "SELECT id FROM project WHERE title = ?";
        ResultSet resultSet = null;
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, projectTitle);
            resultSet = statement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectRepository - getProjectID");
        }
        return resultSet;
    }

    public ResultSet getAllProjectsResultSet() {
        String sql = "SELECT * FROM project";
        ResultSet resultSet = null;
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectRepository - getAllProjects");
        }
        return resultSet;
    }

    public ResultSet getSpecificProjectResultSet(int projectID) {
        String sql = "SELECT * FROM project WHERE id = ?";
        ResultSet resultSet = null;
        try {
            Connection conn = databaseConnection.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAllProjectTitlesResultSet() {
        String sql = "SELECT title FROM project";
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
