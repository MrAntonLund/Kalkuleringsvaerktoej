package com.examprojectsummer2021.models;

/**
 * @author Anton
 */

public class Task {

    private int ID;
    private String title;
    private String description;
    private int price;
    private boolean isFinished;
    private int time;

    public Task(int ID, String title, String description, int price, boolean isFinished, int time) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isFinished = isFinished;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getTime() {
        return time;
    }

    //Could be done in html/thymeleaf
    public String printIsFinished() {
        if (isFinished) {
            return "✔️";
        }
        else {
            return "⭕";
        }
    }

}
