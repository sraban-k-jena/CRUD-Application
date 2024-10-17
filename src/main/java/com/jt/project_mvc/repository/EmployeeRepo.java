package com.jt.project_mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jt.project_mvc.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
