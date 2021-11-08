package com.wyw.service;

import com.wyw.pojo.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);
    int deleteStudentById(int sId);
    int updateStudent(Student student);
    Student searchStudentById(int sId);
    List<Student> ShowAllByBounds();
    List<Student> querryStuByName(String sName);
}
