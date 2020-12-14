package com.icis.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

public class ObjectMapperUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
//    把一个对象转化为字符串功能
    public static void writeObjectToJson(Object obj, HttpServletResponse response){
        String str="";
        if (obj!=null){
            try {
                String string =  MAPPER.writeValueAsString(obj);
                response.getWriter().write(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
