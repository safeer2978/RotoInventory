package com.rotonity.inventory.inventoryapp;

public class User {
   static String username;
    String user_id;
    static boolean isAdmin;
    User(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }
}
