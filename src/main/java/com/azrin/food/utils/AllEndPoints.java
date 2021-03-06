package com.azrin.food.utils;

public class AllEndPoints {
    //user controller
    public static final String USERS_POST = "users";
    public static final String USERS_GET = "users";
    public static final String USERS_GET_BY_ID = "users/{mongo_id}";
    public static final String USERS_PUT = "users/{mongo_id}";
    public static final String USERS_DELETE = "users/{mongo_id}";

    //login controller
    public static final String LOGIN_POST = "login";

    //food controller
    public static final String FOOD_POST = "foods";
    public static final String FOOD_GET_BY_ID = "foods";
    public static final String FOOD_PUT_STATUS = "foods/status";
}
