package com.examprojectsummer2021.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anton & Carsten
 */

class ProjectServiceTest {

    ProjectService projectService;
    TaskService taskService;

    // Class scope
    String[] testArray = {"jowa69"};
    String projectTitle = "testProject";
    int projectID;

    String test1 = "test1";
    String test2 = "test2";
    String test3 = "test3";

    // Test project in production. This is bad practise
    @BeforeEach
    void setUp() {
        projectService = new ProjectService();
        taskService = new TaskService();

        projectService.createNewProject(
                projectTitle,
                "testDesc",
                "jowa69",
                "1970-01-01",
                "1970-01-01",
                testArray);
        projectID = projectService.getProjectID(projectTitle);
    }

    // Deletion of project as soon as we're done testing.
    // Thanks to SQL code 'DELETE CASCADE' we only need to delete the project, and it will sort out
    // its' children (tasks)
    @AfterEach
    void cleanUp(){
        projectService.deleteProject(projectTitle);
    }

    @ParameterizedTest
    @CsvSource(value = {"100:150:200:450","1:104:84:189" }, delimiter = ':')
    void getTotalPrice(int a, int b, int c, int expected) {

        // Arrange
        taskService.createTask(test1,"desc",testArray,"jowa69",a,10,projectID);
        taskService.createTask(test2,"desc",testArray,"jowa69",b,10,projectID);
        taskService.createTask(test3,"desc",testArray,"jowa69",c,10,projectID);

        // Act
        int priceTest = projectService.getTotalPrice(projectID);

        // Assert
        assertEquals(expected,priceTest);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:10:10:30","5:7:8:20","20:20:20:60"}, delimiter = ':')
    void getTotalHours(int a, int b, int c, int expected) {

        // Arrange
        taskService.createTask(test1,"desc",testArray,"jowa69",10,a,projectID);
        taskService.createTask(test2,"desc",testArray,"jowa69",10,b,projectID);
        taskService.createTask(test3,"desc",testArray,"jowa69",10,c,projectID);

        // Act
        int timeTest = projectService.getTotalHours(projectID);

        // Assert
        assertEquals(expected, timeTest);
    }
}