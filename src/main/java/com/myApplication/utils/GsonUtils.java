package com.myApplication.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonUtils {
    private static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().disableHtmlEscaping().create();

    private GsonUtils() {

    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type typeOfT) {
        return (T) gson.fromJson(json, typeOfT);
    }

    public static String toJson(Object obj, Type typeOfT) {
        return gson.toJson(obj, typeOfT);
    }

}
