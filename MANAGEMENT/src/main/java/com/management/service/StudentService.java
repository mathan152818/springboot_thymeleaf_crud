package com.management.service;

import java.util.List;

import com.management.entity.Student;

public interface StudentService {

	List<Student> getallStudents();
	
	Student save(Student student);
	
	Student updateStudent(Student student);

	Student getStudentById(Long id);

	void deleteById(Long id);
	
}
