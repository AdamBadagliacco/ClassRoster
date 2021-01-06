package com.sg.classroster.dao;

import java.util.List;

import com.sg.classroster.entity.Teacher;

public interface TeacherDao {
    Teacher getTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}