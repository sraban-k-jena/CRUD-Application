package com.jt.project_mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.project_mvc.model.Employee;
import com.jt.project_mvc.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee) {

        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> retriveAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        if (!employeeRepo.existsById(id)) {
            throw new IllegalArgumentException("Employee id :" + id + "does not exist .");
        }

        employee.setId(id);
        return employeeRepo.save(employee);

    }

    @Override
    public void deleteId(int id) {
        if (!employeeRepo.existsById(id)) {
            throw new IllegalArgumentException("Employee id :" + id + "does not exist .");
        }
        employeeRepo.deleteById(id);
    }
}
