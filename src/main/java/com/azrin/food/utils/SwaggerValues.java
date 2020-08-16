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
}
