package com.processing.taskmanagementsystem.entity;

public enum Status {

    ToDo("To Do"),
    InProgress("In Progress"),
    Blocked("Blocked"),
    Completed("Completed"),
    Cancelled("Cancelled");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}