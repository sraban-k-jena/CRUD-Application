package com.jt.project_mvc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jt.project_mvc.model.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> retriveAll();

    Employee updateEmployee(Employee employee, int id);

    void deleteId(int id);
}
