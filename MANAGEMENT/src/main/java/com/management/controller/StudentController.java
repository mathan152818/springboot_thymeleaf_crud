package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.entity.Student;
import com.management.serviceimpl.StudentServiceImpl;

import jakarta.validation.Valid;

@Controller
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;
	

	/*
	 * @GetMapping("/home") public String home() { return
	 * "redirect:http://localhost:1000/home"; }
	 */

	@GetMapping("/students")
	public String getStudentServiceImpl(Model model) {
		model.addAttribute("students", studentServiceImpl.getallStudents());
		return "students";
	}

	@GetMapping("/students/addnew")
	public String create(Model model) {

		model.addAttribute("allGenders", List.of("Male", "Female", "Others"));
		model.addAttribute("allDepartment",
				List.of("Computer Science", "Petrochemical", "Electronic Communication", "Mechanical", "Textile"));
		model.addAttribute("addnew", new Student());
		return "addnew";
	}

	@PostMapping("/students")
	public String save(@ModelAttribute("addnew") @Valid Student student, BindingResult bindingResult, Errors errors,
			Model model) {

		Boolean err = bindingResult.hasErrors();
		if (err) {
			model.addAttribute("addnew", student);
			model.addAttribute("allGenders", List.of("Male", "Female", "Others"));
			model.addAttribute("allDepartment",
					List.of("Computer Science", "Petrochemical", "Electronic Communication", "Mechanical", "Textile"));
			return "addnew";
		}
		studentServiceImpl.save(student);

		return "redirect:/students";

	}

	@GetMapping("/students/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		model.addAttribute("student", studentServiceImpl.getStudentById(id));

		model.addAttribute("allGenders", List.of("Male", "Female", "Others"));
		model.addAttribute("allDepartment",
				List.of("Computer Science", "Petrochemical", "Electronic Communication", "Mechanical", "Textile"));

		return "update";
	}

	@PostMapping("/students/{id}")
	public String save(@PathVariable Long id, @ModelAttribute("student") @Valid Student student,
			BindingResult bindingResult, Errors errors, Model model) {

		// get student details using id
		Student stu = studentServiceImpl.getStudentById(id);

		// update the student details
		stu.setId(id);
		stu.setFullname(student.getFullname());
		stu.setGender(student.getGender());
		stu.setEmail(student.getEmail());
		stu.setDepartment(student.getDepartment());

		Boolean err = bindingResult.hasErrors();
		if (err) {
			model.addAttribute("student", student);
			model.addAttribute("allGenders", List.of("Male", "Female", "Others"));
			model.addAttribute("allDepartment",
					List.of("Computer Science", "Petrochemical", "Electronic Communication", "Mechanical", "Textile"));
			return "update";
		}

		// save the updated details
		studentServiceImpl.updateStudent(stu);
		return "redirect:/students";
	}

	@GetMapping("students/delete/{id}")
	public String delete(@PathVariable Long id) {

		studentServiceImpl.deleteById(id);
		return "redirect:/students";
	}

}
