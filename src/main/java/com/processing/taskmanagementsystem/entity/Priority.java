package com.processing.taskmanagementsystem.entity;

public enum Priority {

    High("High"),
    Normal("Normal"),
    Low("Low");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
