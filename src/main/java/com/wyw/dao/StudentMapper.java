package com.wyw.dao;

import com.wyw.pojo.Student;

import java.security.Provider;
import java.util.List;

public interface StudentMapper {

    int addStudent(Student student);
    int deleteStudentById(int sId);
    int updateStudent(Student student);
    Student searchStudentById(int sId);
    List<Student> ShowAllByBounds();
    List<Student> querryStuByName(String sName);
}
