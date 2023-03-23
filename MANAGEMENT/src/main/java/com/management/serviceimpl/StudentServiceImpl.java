package com.management.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entity.Student;
import com.management.repository.StudentRepository;
import com.management.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService  {

	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	public List<Student> getallStudents() {
		return studentRepository.findAll();
	}


	@Override
	public Student save(Student student) {
		
		return studentRepository.save(student);
	}


	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}


	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
		
	}


	
	}


