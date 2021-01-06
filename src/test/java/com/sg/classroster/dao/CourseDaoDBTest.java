package com.sg.classroster.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.classroster.entity.Course;
import com.sg.classroster.entity.Student;
import com.sg.classroster.entity.Teacher;

//import org.junit.jupiter.api.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDaoDBTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public CourseDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        for(Teacher teacher : teachers) {
            teacherDao.deleteTeacherById(teacher.getId());
        }
        
        List<Student> students = studentDao.getAllStudents();
        for(Student student : students) {
            studentDao.deleteStudentById(student.getId());
        }
        
        List<Course> courses = courseDao.getAllCourses();
        for(Course course : courses) {
            courseDao.deleteCourseById(course.getId());
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testAddAndGetCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

    @Test
    public void testGetAllCourses() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course course2 = new Course();
        course2.setName("Test Course Name 2");
        course2.setTeacher(teacher);
        course2.setStudents(students);
        course2 = courseDao.addCourse(course2);
        
        List<Course> courses = courseDao.getAllCourses();
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertTrue(courses.contains(course2));
    }

    @Test
    public void testUpdateCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
        
        course.setName("New Test Course Name");
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        students.add(student2);
        course.setStudents(students);
        
        courseDao.updateCourse(course);
        
        assertNotEquals(course, fromDao);
        
        fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

    @Test
    public void testDeleteCourseById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
        
        courseDao.deleteCourseById(course.getId());
        
        fromDao = courseDao.getCourseById(course.getId());
        assertNull(fromDao);
    }

    @Test
    public void testGetCoursesForTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test Teacher First 2");
        teacher2.setLastName("Test Teacher Last 2");
        teacher2.setSpecialty("Test Teacher Specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course course2 = new Course();
        course2.setName("Test Course Name");
        course2.setTeacher(teacher2);
        course2.setStudents(students);
        course2 = courseDao.addCourse(course2);
        
        Course course3 = new Course();
        course3.setName("Test Course Name");
        course3.setTeacher(teacher);
        course3.setStudents(students);
        course3 = courseDao.addCourse(course3);
        
        List<Course> courses = courseDao.getCoursesForTeacher(teacher);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertFalse(courses.contains(course2));
        assertTrue(courses.contains(course3));
    }

    @Test
    public void testGetCoursesForStudent() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        
        List<Student> students2 = new ArrayList<>();
        students2.add(student2);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course course2 = new Course();
        course2.setName("Test Course Name");
        course2.setTeacher(teacher);
        course2.setStudents(students2);
        course2 = courseDao.addCourse(course2);
        
        Course course3 = new Course();
        course3.setName("Test Course Name");
        course3.setTeacher(teacher);
        course3.setStudents(students);
        course3 = courseDao.addCourse(course3);
        
        List<Course> courses = courseDao.getCoursesForStudent(student);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertFalse(courses.contains(course2));
        assertTrue(courses.contains(course3));
    }
}