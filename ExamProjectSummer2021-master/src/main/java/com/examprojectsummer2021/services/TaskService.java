package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.repositories.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Anton, Julius, Carsten
 */

public class TaskService {

    TaskRepository taskRepository = new TaskRepository();

    // ------ SETTERS ------ //

    public void createTask(String taskTitle, String taskDescription, String[] taskUsers, String taskOwner, int taskPrice, int taskTime, int projectID) {


        taskRepository.createNewTask(taskTitle, taskDescription, taskPrice, taskTime, taskOwner, projectID);

        linkUserAndTask(taskUsers, taskTitle);
    }

    public void changeTaskFinished(int taskID) {

        Task task = getTask(taskID);

        boolean state;

        state = !task.isFinished();

        taskRepository.changeTaskFinished(state, taskID);

    }

    public void linkUserAndTask(String[] taskUsers, String taskTitle) {
        int taskID = getTaskID(taskTitle);

        for (String s : taskUsers) {
            taskRepository.linkUserAndTask(s, taskID);
        }
    }

    public void deleteTask(int taskID) {
        taskRepository.deleteTask(taskID);
    }

    // ------ GETTERS ------ //

    public Task getTask(int taskID) {
        Task task = null;
        ResultSet resultSet = taskRepository.getTaskResultSet(taskID);

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);                   // id
                String title = resultSet.getString(2);          // title
                String description = resultSet.getString(3);    // description
                int price = resultSet.getInt(4);                // price
                boolean isFinished = resultSet.getBoolean(5);   // isFinished
                int time = resultSet.getInt(6);                 // time

                task = new Task(id, title, description, price, isFinished, time);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return task;
    }

    public int getTaskID(String taskTitle) {
        int taskID = -1;
        ResultSet resultSet = taskRepository.getTaskIDResultSet(taskTitle);

        try {
            while (resultSet.next()) {
                taskID = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskService - getTaskID");
        }
        return taskID;
    }

    public ArrayList<Task> getTasksFromProject(int projectID) {
        ArrayList<Task> taskList = new ArrayList<>();
        ResultSet resultSet = taskRepository.getTasksFromProjectResultSet(projectID);

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);                   // id
                String title = resultSet.getString(2);          // title
                String description = resultSet.getString(3);    // description
                int price = resultSet.getInt(4);                // price
                boolean isFinished = resultSet.getBoolean(5);   // isFinished
                int time = resultSet.getInt(6);                 // time

                Task tmpTask = new Task(id, title, description, price, isFinished, time);

                taskList.add(tmpTask);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return taskList;
    }

    public ArrayList<String> getAllTaskTitles() {
        ArrayList<String> allTaskTitles = new ArrayList<>();
        ResultSet resultSet = taskRepository.getAllTaskTitlesResultSet();

        try {
            while (resultSet.next()) {
                allTaskTitles.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskService - getAllTaskTitles");
        }
        return allTaskTitles;
    }
}


