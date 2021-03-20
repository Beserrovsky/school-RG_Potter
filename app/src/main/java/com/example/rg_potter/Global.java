package com.example.rg_potter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final public class Global {

    // GLOBAL CONSTS

    public static final String API_URL = "https://harry-potter-deploy.herokuapp.com";
    public static final String CHARACTERS_ENDPOINT = "/api/1/characters/all";
    public static final String LOCAL_JSON = "characters.json";

    // GLOBAL VARS

    public static boolean SAFE_INSTALL = true;
    public static int user_index = 0;

    // Global Objects

    public static Character[] characters;
    public static List<User> users = new ArrayList<User>();
}
