package com.zjsu.yyd.course.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponse {

    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        map.put("message", "Success");
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> error(int code, String message) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", null);
        return map;
    }
}
