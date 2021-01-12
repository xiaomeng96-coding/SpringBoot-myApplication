package com.myApplication.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class Student {
    private String id;
    private String name;
    private String unicode;
}
