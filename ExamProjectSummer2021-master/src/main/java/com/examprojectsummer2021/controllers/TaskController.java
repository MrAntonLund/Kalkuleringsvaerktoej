package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ProjectService;
import com.examprojectsummer2021.services.TaskService;
import com.examprojectsummer2021.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Carsten & Anton
 */

@Controller
public class TaskController {

    private ProjectService projectService = new ProjectService();
    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();

    // ------------ CREATE TASK ------------ //
    @GetMapping("/view/{projectTitle}/createtask")
    public String renderNewTask(Model model, @PathVariable("projectTitle") String projectTitle) {
        int projectID = projectService.getProjectID(projectTitle);
        model.addAttribute("projectUsers", userService.getUsersFromProject(projectID));
        model.addAttribute("taskTitles", taskService.getAllTaskTitles());
        model.addAttribute("projectTitle", projectTitle);

        return "task/createtask.html";
    }

    @PostMapping("/createtask")
    public String createNewTask(@RequestParam(name = "title") String taskTitle,
                                @RequestParam(name = "description") String taskDescription,
                                @RequestParam(name = "time") int taskTime,
                                @RequestParam(name = "username") String[] taskUsers,
                                @RequestParam(name = "price", defaultValue = "0") int taskPrice,
                                @RequestParam(name = "projectTitle") String projectTitle) {

        //temp user - planned for login
        String taskOwner = "jowa69";
        int projectID = projectService.getProjectID(projectTitle);
        taskService.createTask(taskTitle, taskDescription, taskUsers, taskOwner, taskPrice, taskTime, projectID);

        return "redirect:/view/" + projectTitle;
    }


    // ------------ EDIT TASK ------------ //
    @GetMapping("/view/{projectTitle}/{taskTitle}")
    public String renderUpdateTask(@PathVariable("projectTitle") String projectTitle, @PathVariable("taskTitle") String taskTitle, Model model) {
        int taskID = taskService.getTaskID(taskTitle);
        model.addAttribute("projectTitle", projectTitle);
        model.addAttribute("task", taskService.getTask(taskID));
        model.addAttribute("users", userService.getUsersFromTask(taskID));

        return "task/updatetask.html";
    }

    @PostMapping("/change_finished_status")
    public String ChangeStatus(@RequestParam(name = "projectTitle") String projectTitle,
                                     @RequestParam(name = "taskID") int taskID) {

        taskService.changeTaskFinished(taskID);

        return "redirect:/view/" + projectTitle;
    }


    // ------------ DELETE TASK ---------- //
    @PostMapping("/deletetask")
    public String deleteTask(@RequestParam(name = "taskID") int taskID,
                             @RequestParam(name = "projectTitle") String projectTitle) {

        taskService.deleteTask(taskID);

        return "redirect:/view/" + projectTitle;
    }
}

