package com.wyw.service;

import com.wyw.dao.StudentMapper;
import com.wyw.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int deleteStudentById(int sId) {
        return studentMapper.deleteStudentById(sId);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public Student searchStudentById(int sId) {
        return studentMapper.searchStudentById(sId);
    }

    @Override
    public List<Student> ShowAllByBounds() {
        return studentMapper.ShowAllByBounds();
    }

    @Override
    public List<Student> querryStuByName(String sName) {
        return studentMapper.querryStuByName(sName);
    }
}
