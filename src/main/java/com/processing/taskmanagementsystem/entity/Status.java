package com.processing.taskmanagementsystem.entity;

public enum Status {

    Scheduled("Scheduled"),
    NotStarted("Not Started"),
    InProgress("In Progress"),
    Blocked("Blocked"),
    OnTest("On Test"),
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