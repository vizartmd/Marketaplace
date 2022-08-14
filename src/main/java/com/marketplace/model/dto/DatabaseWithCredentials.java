package com.marketplace.model.dto;

public class DatabaseWithCredentials {
    private static String typeOfDatabase;
    private static String userName;
    private static String password;

    public static String getTypeOfDatabase() {
        return String.valueOf(typeOfDatabase);
    }

    public static void setTypeOfDatabase(String typeOfDatabase) {
        DatabaseWithCredentials.typeOfDatabase = typeOfDatabase;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        DatabaseWithCredentials.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DatabaseWithCredentials.password = password;
    }
}
