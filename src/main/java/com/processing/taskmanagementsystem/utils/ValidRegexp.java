package com.processing.taskmanagementsystem.utils;

/**
 * @author Artur Tomeyan
 * @date 25.02.2024
 */
public final class ValidRegexp {

    private ValidRegexp() {
    }

    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,12}$";
}