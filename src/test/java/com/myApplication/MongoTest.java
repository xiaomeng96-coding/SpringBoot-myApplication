package com.myApplication;

import com.mongodb.client.result.UpdateResult;
import com.myApplication.entity.Student;
import com.myApplication.entity.Teacher;
import com.myApplication.page.PageHelper;
import com.myApplication.utils.MongoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MongoTest {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    MongoUtil mongoUtil;

    @Test
    void addStudent() {
        int a = 1;
        while (a <= 100) {
            Student student = new Student();
            student.setName("name" + a);
            student.setUnicode(UUID.randomUUID().toString());
            Student insert = mongoTemplate.insert(student);
            System.out.println(insert);
            a++;
        }
    }


    @Test
    void findByName() {
        Query query = new Query(Criteria.where("name").is("name1"));
        List<Student> students = mongoTemplate.find(query, Student.class);
        System.out.println(students);
    }

    @Test
    void findByNameAndUnicode() {
//        Query query = new Query(Criteria.where("name").is("name1").and("unicode").is("e24928f8-ef7a-479d-8211-fb4e01c344f5"));
        Student student = new Student();
        student.setName("name1");
        student.setUnicode("9e357e59-91fb-434a-b9e5-a6caba58d874");
        Query query = new Query(Criteria.byExample(student));
        List<Student> students = mongoTemplate.find(query, Student.class);
        System.out.println(students);
    }

    @Test
    void addTeacher() {
        List<String> str = new ArrayList<>();
        str.add("name1");
        str.add("name2");
        Query query = new Query(Criteria.where("name").in(str));
        List<Student> students = mongoTemplate.find(query, Student.class);
        int a = 1;
        while (a < 100) {
            Teacher teacher = new Teacher();
            teacher.setName("teacher");
            teacher.setProject("project" + a);
            teacher.setStudents(students);
            Teacher insert = mongoTemplate.insert(teacher);
            System.out.println(insert);
            a++;
        }
    }

    /**
     * 查询list中对象的值
     */
    @Test
    void findTeacher() {
        Query query = new Query(Criteria.where("students.name").is("name1").and("project").is("project1"));
        List<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        System.out.println(teachers);
    }

    /**
     * 分页查询
     */
    @Test
    void findByPage() {
//        Query query = new Query(Criteria.where("students.name").is("name1").and("project").is("project1"));
        Query query = new Query(new Criteria());
//        query.with(Sort.by(Sort.Direction.DESC, "time"));
        mongoUtil.start(2, 2, query);
        List<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        long count = mongoTemplate.count(query, Teacher.class);
        PageHelper pageHelper = mongoUtil.pageHelper(count, teachers);
        System.out.println(pageHelper);
    }

    /**
     * 更新操作
     */
    @Test
    void update() {
        Query query = new Query();
        query.addCriteria(Criteria.where("project").is("project2"));
        Update update = new Update();
//        //直接更新
//        update.set("students", "更新成功");
//        //文档不存在的话会插入一条新的记录
//        update.setOnInsert("students", "更新成功1");
//        //移除这个键
//        update.unset("students");
        //加入到一个集合中,不会重复
//        update.addToSet("students", "更新成功3");
        //需要传值,会清空其他属性
//        update.addToSet("students");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Teacher.class);
        System.out.println(updateResult);
    }
}

