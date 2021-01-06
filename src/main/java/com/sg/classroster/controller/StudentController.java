package com.sg.classroster.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sg.classroster.dao.CourseDao;
import com.sg.classroster.dao.StudentDao;
import com.sg.classroster.dao.TeacherDao;
import com.sg.classroster.entity.Student;

@Controller
public class StudentController {

		@Autowired
		TeacherDao teacherDao;

	   @Autowired
	   StudentDao studentDao;

	   @Autowired
	   CourseDao courseDao;
	   
	   Set<ConstraintViolation<Student>> violations = new HashSet<>();
	   
	   @GetMapping("students")
	    public String displayStudents(Model model) {
	        List<Student> students = studentDao.getAllStudents();
	        model.addAttribute("students", students);
	        model.addAttribute("errors", violations);
	        return "students";
	    }
	   
	   @PostMapping("addStudent")
	    public String addStudent(String firstName, String lastName) {
	        Student student = new Student();
	        student.setFirstName(firstName);
	        student.setLastName(lastName);
	        
	        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
	        violations = validate.validate(student);

	        if(violations.isEmpty()) {
	        	studentDao.addStudent(student);
	        }
	        
	        return "redirect:/students";
	    }
	   
	   @GetMapping("deleteStudent")
	    public String deleteStudent(Integer id) {
	        studentDao.deleteStudentById(id);
	        return "redirect:/students";
	    }
	   
	   @GetMapping("editStudent")
	    public String editStudent(Integer id, Model model) {
	        Student student = studentDao.getStudentById(id);
	        model.addAttribute("student", student);
	        return "editStudent";
	    }
	
	   @PostMapping("editStudent")
	   public String performEditStudent(@Valid Student student, BindingResult result) {
		    if(result.hasErrors()) {
		        return "editStudent";
		    }
		    studentDao.updateStudent(student);
		    return "redirect:/students";
	   }
}
