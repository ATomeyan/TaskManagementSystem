package com.processing.taskmanagementsystem.utils;

public final class DBConstants {

    private DBConstants() {
    }

    // Table names
    public static final String TB_ROLE = "tb_role";
    public static final String TB_TASK = "tb_task";
    public static final String TB_TASK_USER = "tb_task_user";
    public static final String TB_USER = "tb_user";
    public static final String TB_USER_ROLES = "tb_user_roles";

    // Base entity fields
    public static final String CRATED = "created";
    public static final String UPDATED = "updated";

    // Role table fields
    public static final String NAME = "name";

    // Task table fields
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DUE_DATE = "due_date";
    public static final String PRIORITY = "priority";
    public static final String STATUS = "status";

    // User table fields
}