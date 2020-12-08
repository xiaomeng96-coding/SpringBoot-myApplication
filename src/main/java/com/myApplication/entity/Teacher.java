package com.myApplication.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "teacher")
public class Teacher {
    private String id;
    private String name;
    private String project;
    private List<Student> students;
}
