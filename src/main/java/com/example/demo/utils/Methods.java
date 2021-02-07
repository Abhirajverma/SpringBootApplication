package com.example.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Methods {

  public static String mapToJson(Object object) throws Exception {
    ObjectMapper objectMapper=new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.writeValueAsString(object);
  }

}
