package com.sg.classroster.dao;

import java.util.List;

import com.sg.classroster.entity.Course;
import com.sg.classroster.entity.Student;
import com.sg.classroster.entity.Teacher;

public interface CourseDao {
    Course getCourseById(int id);
    List<Course> getAllCourses();
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);
    
    List<Course> getCoursesForTeacher(Teacher teacher);
    List<Course> getCoursesForStudent(Student student);
}