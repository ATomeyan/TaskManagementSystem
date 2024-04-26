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
    public static final String TB_COMMENT = "tb_comment";
    public static final String TB_TASK_COMMENT = "tb_task_comment";
    public static final String TB_USER_COMMENT = "tb_user_comment";

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
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ENABLED = "enabled";

    // Mapped tables
    public static final String UUID = "uuid";
    public static final String USER_UID = "user_uid";
    public static final String ROLE_UID = "role_uid";
    public static final String TASK_UID = "task_uid";
    public static final String COMMENT_UID = "comment_uid";
    public static final String USER = "user";
    public static final String TASK = "task";
    public static final String ROLE = "role";
    public static final String COMMENT = "comment";

    public static final String CONTENT = "content";
}