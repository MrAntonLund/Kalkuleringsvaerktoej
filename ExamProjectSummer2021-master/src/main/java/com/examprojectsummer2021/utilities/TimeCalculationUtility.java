package com.examprojectsummer2021.utilities;

import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.services.ProjectService;
import com.examprojectsummer2021.services.TaskService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Carsten
 */
public class TimeCalculationUtility {

    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();

    public double workingHoursPerDay(int projectID) {
        double combinedTaskTime = combinedTaskTime(projectID);
        int daysToProjectDeadline = daysToProjectDeadline(projectID);
        if (daysToProjectDeadline == 0) {
            return -1;
        }

        return combinedTaskTime / daysToProjectDeadline;
    }

    private int combinedTaskTime(int projectID) {
        ArrayList<Task> projectTasks = taskService.getTasksFromProject(projectID);
        int combinedTaskTime = 0;

        for (int i = 0; i < projectTasks.size(); i++) {
            if (!projectTasks.get(i).isFinished())
            combinedTaskTime += projectTasks.get(i).getTime();
        }

        return combinedTaskTime;
    }

    private int daysToProjectDeadline(int projectID) {
        //Roland - stackoverflow.com -- https://stackoverflow.com/a/44942039

        LocalDate startDate = convertDateToLocalDate(projectService.getSpecificProject(projectID).getStartDate());
        LocalDate deadline = convertDateToLocalDate(projectService.getSpecificProject(projectID).getDeadline());

        int startDayOfWeek = startDate.getDayOfWeek().getValue();
        int deadlineDayOfWeek = deadline.getDayOfWeek().getValue();

        long daysBetween = ChronoUnit.DAYS.between(startDate, deadline);

        int daysToProjectDeadline = (int) (daysBetween - 2 * ( daysBetween / 7));

        if (daysBetween % 7 != 0) {
            if (startDayOfWeek == 7) {
                daysToProjectDeadline -= 1;
            } else if (deadlineDayOfWeek == 7) {
                daysToProjectDeadline -= 1;
            } else if (deadlineDayOfWeek < startDayOfWeek) {
                daysToProjectDeadline -= 2;
            }
        }
        return daysToProjectDeadline;
    }


    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
