package com.sg.classroster.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import com.sg.classroster.entity.Teacher;

@Controller
public class TeacherController {

   @Autowired
   TeacherDao teacherDao;

   @Autowired
   StudentDao studentDao;

   @Autowired
   CourseDao courseDao;
   
   Set<ConstraintViolation<Teacher>> violations = new HashSet<>();
   
   @GetMapping("teachers")
   public String displayTeachers(Model model) {
       List<Teacher> teachers = teacherDao.getAllTeachers();
       model.addAttribute("teachers", teachers);
       
       model.addAttribute("errors", violations);
       
       return "teachers";
   }
   
   @PostMapping("addTeacher")
   public String addTeacher(HttpServletRequest request) {
       String firstName = request.getParameter("firstName");
       String lastName = request.getParameter("lastName");
       String specialty = request.getParameter("specialty");
       
       Teacher teacher = new Teacher();
       teacher.setFirstName(firstName);
       teacher.setLastName(lastName);
       teacher.setSpecialty(specialty);
       
       Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
       violations = validate.validate(teacher);

       if(violations.isEmpty()) {
           teacherDao.addTeacher(teacher);
       }
       
       return "redirect:/teachers";
   }
   
   @GetMapping("deleteTeacher")
   public String deleteTeacher(HttpServletRequest request) {
       int id = Integer.parseInt(request.getParameter("id"));
       teacherDao.deleteTeacherById(id);
       
       return "redirect:/teachers";
   }
   
   @GetMapping("editTeacher")
   public String editTeacher(HttpServletRequest request, Model model) {
       int id = Integer.parseInt(request.getParameter("id"));
       Teacher teacher = teacherDao.getTeacherById(id);
       
       model.addAttribute("teacher", teacher);
       return "editTeacher";
   }
   
   @PostMapping("editTeacher")
   public String performEditTeacher(@Valid Teacher teacher, BindingResult result) {
       if(result.hasErrors()) {
           return "editTeacher";
       }
       teacherDao.updateTeacher(teacher);
       return "redirect:/teachers";
   }
}