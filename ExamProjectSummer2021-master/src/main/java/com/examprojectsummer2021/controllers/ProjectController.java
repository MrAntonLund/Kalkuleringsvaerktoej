package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ProjectService;
import com.examprojectsummer2021.services.TaskService;
import com.examprojectsummer2021.services.UserService;
import com.examprojectsummer2021.utilities.TimeCalculationUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Carsten, Anton
 */

@Controller
public class ProjectController {

    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private TimeCalculationUtility timeCalculationUtility = new TimeCalculationUtility();

    // ------------ DASHBOARD ------------ //
    @GetMapping("/view/dashboard")
    public String renderDashboard(Model model) {
        model.addAttribute("list", projectService.getAllProjects());
        model.addAttribute("users", userService.getAllUsers());

        return "index/dashboard.html";
    }


    // ------------ CREATE PROJECT ------------ //
    @GetMapping("/view/createproject")
    public String renderNewProject(Model model) {

        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("projectTitles", projectService.getAllProjectTitles());

        return "project/createproject.html";
    }

    @PostMapping("/createproject")
    public String createNewProject(@RequestParam(name = "title") String projectTitle,
                                   @RequestParam(name = "description") String projectDescription,
                                   @RequestParam(name = "startdate") String projectStartDate,
                                   @RequestParam(name = "deadline") String projectDeadline,
                                   @RequestParam(name = "username", required = false) String[] projectUsers) {
        //temp user - planned for login
        String projectOwner = "jowa69";

        projectService.createNewProject(projectTitle, projectDescription, projectOwner, projectStartDate, projectDeadline, projectUsers);

        return "redirect:/view/" + projectTitle;
    }


    // ------------ EDIT PROJECT ------------ //
    @GetMapping("/view/{title}")
    public String renderUpdateProject(@PathVariable("title") String projectTitle, Model model) {
        int projectID = projectService.getProjectID(projectTitle);

        model.addAttribute("project", projectService.getSpecificProject(projectID));
        model.addAttribute("users", userService.getUsersFromProject(projectID));
        model.addAttribute("tasks", taskService.getTasksFromProject(projectID));
        model.addAttribute("time", timeCalculationUtility.workingHoursPerDay(projectID));
        model.addAttribute("totalPrice",projectService.getTotalPrice(projectID));
        model.addAttribute("totalHours",projectService.getTotalHours(projectID));

        return "project/updateproject.html";
    }

    @PostMapping("/change_project_status")
    public String ChangeStatus(@RequestParam(name = "projectTitle") String projectTitle) {

        projectService.changeProjectStatus(projectTitle);

        return "redirect:/view/" + projectTitle;
    }


    // ----------- Delete Project ------------//
    @PostMapping("/deleteproject")
    public String deleteProject(@RequestParam(name = "projectTitle") String projectTitle) {
        projectService.deleteProject(projectTitle);
        return "redirect:/view/dashboard";
    }
}
