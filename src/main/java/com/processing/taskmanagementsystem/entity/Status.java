package com.processing.taskmanagementsystem.entity;

public enum Status {

    Scheduled("Scheduled"),
    Notstarted("Not Started"),
    Inprogress("In Progress"),
    Blocked("Blocked"),
    Ontest("On Test"),
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