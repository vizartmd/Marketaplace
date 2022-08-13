package com.marketplace.model.dto;

public class MySQLCredentials {
    private static String userName;
    private static String password;

    public static void setUserName(String userName) {
        MySQLCredentials.userName = userName;
    }

    public static void setPassword(String password) {
        MySQLCredentials.password = password;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
}
