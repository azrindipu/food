package com.azrin.food.utils;

public class SwaggerValues {

    /*----------------------------- swagger status ------------------------*/
    public static final String STATUS_OK = "OK";
    public static final String STATUS_BAD_REQUEST = "Bad Request";
    public static final String STATUS_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String STATUS_NOT_FOUND = "Not Found";

    /*----------------------------- user controller ------------------------*/
    public static final String USER_CONTROLLER_VALUE = "Users";

    public static final String USER_CONTROLLER_USERS_POST_VALUE = "Create user";
    public static final String USER_CONTROLLER_USERS_POST_NOTES = "Create a particular user";
    public static final int USER_CONTROLLER_USER_POST_POSSITION = 1;

    public static final String USER_CONTROLLER_USERS_GET_VALUE = "Get users";
    public static final String USER_CONTROLLER_USERS_GET_NOTES = "Get users [paginated]";
    public static final int USER_CONTROLLER_USER_GET_POSSITION = 2;

    public static final String USER_CONTROLLER_USERS_DELETE_VALUE = "Delete user";
    public static final String USER_CONTROLLER_USERS_DELETE_NOTES = "Delete User";
    public static final int USER_CONTROLLER_USER_DELETE_POSSITION = 3;

    public static final String USER_CONTROLLER_USERS_GET_BY_ID_VALUE = "Get user";
    public static final String USER_CONTROLLER_USERS_GET_BY_ID_NOTES = "Get user by ID";
    public static final int USER_CONTROLLER_USER_GET_BY_ID_POSSITION = 4;

    /*----------------------------- login controller ------------------------*/
    public static final String LOGIN_CONTROLLER_VALUE = "Login";

    public static final String LOGIN_CONTROLLER_LOGIN_POST_VALUE = "Login user";
    public static final String LOGIN_CONTROLLER_LOGIN_POST_NOTES = "Login";
    public static final int Login_CONTROLLER_Login_POST_POSSITION = 1;

    /*----------------------------- food controller ------------------------*/
    public static final String FOOD_CONTROLLER_VALUE = "food";

    public static final String FOOD_CONTROLLER_POST_VALUE = "Create food post";
    public static final String FOOD_CONTROLLER_POST_NOTES = "Create a particular food post";
    public static final int FOOD_CONTROLLER_POST_POSSITION = 1;

    public static final String FOOD_CONTROLLER_GET_BY_ID_VALUE = "Get food post";
    public static final String FOOD_CONTROLLER_GET_BY_ID_NOTES = "Get a particular food post";
    public static final int FOOD_CONTROLLER_GET_BY_ID_POSSITION = 2;

    public static final String FOOD_CONTROLLER_PUT_STATUS_VALUE = "Update food status";
    public static final String FOOD_CONTROLLER_PUT_STATUS_NOTES = "Update a particular food post status";
    public static final int FOOD_CONTROLLER_PUT_STATUS_POSSITION = 3;
}
