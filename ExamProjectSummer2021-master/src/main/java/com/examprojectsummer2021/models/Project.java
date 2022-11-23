package com.examprojectsummer2021.models;

import com.examprojectsummer2021.services.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Anton, Carsten
 */

public class Project {

    private TaskService taskService = new TaskService();

    private int ID;
    private String title;
    private String description;
    private String owner;
    private Date startDate;
    private Date deadline;
    private boolean isFinished;


    public Project(int projectID, String title, String description, String owner, String startDate, String deadline, boolean isFinished) {
        this.ID = projectID;
        this.title = title;
        this.description = description;
        this.owner = owner;

        try {
            this.startDate = new SimpleDateFormat("yy-MM-dd").parse(startDate);
            this.deadline = new SimpleDateFormat("yy-MM-dd").parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Project - Constructor");
        }

        this.isFinished = isFinished;


    }

    // -- GETTERS -- //

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getDeadlineAsString() {

        String deadlineString;
        String pattern = "dd-MM-yy";
        DateFormat df = new SimpleDateFormat(pattern);

        deadlineString = df.format(deadline.getTime());

        return deadlineString;
    }

    public String getStartDateAsString(){

        String deadlineString;
        String pattern = "dd-MM-yy";
        DateFormat df = new SimpleDateFormat(pattern);

        deadlineString = df.format(startDate.getTime());

        return deadlineString;
    }

    public long countdown() {

        Date today = new Date();

        long diff = deadline.getTime() - today.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

    }

    // Following methods shouldn't be located here
    // in the 'Project' model
    public ArrayList<Task> getTasks(){
        return taskService.getTasksFromProject(ID);
    }

    public String getTasksStatus(){
        ArrayList<Task> tmpTasks = taskService.getTasksFromProject(ID);
        int total = tmpTasks.size();

        int positive = 0;
        for (int i = 0; i < tmpTasks.size(); i++) {
            if (tmpTasks.get(i).isFinished()) {
                positive++;
            }
        }
        String result = positive + "/" + total;

        return result;
    }

}
