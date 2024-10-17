package com.jt.project_mvc.controller;

import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Paths;
import com.jt.project_mvc.model.Employee;
import com.jt.project_mvc.service.EmployeeService;

import jakarta.validation.Valid;

import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.nio.file.Files;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EmployeeController {

    @Autowired
    public EmployeeService service;

    @GetMapping({ "/", "/base" })
    public String base(Model model) {
        List<Employee> employees = service.retriveAll();
        model.addAttribute("images", employees);
        return "base";
    }

    @GetMapping("/edit_emp")
    public String editEmployee() {
        return "edit_emp";
    }

    @GetMapping("/emp_save")
    public String employeeSave() {
        return "emp_save";
    }

    @PostMapping("/submitEmployee")
    public String saveEmployee(@Valid @RequestParam String name, @RequestParam String email,
            @RequestParam String address,
            @RequestParam String gender, @RequestParam String password, @RequestParam LocalDate dob,
            @RequestParam MultipartFile profilePic) {

        // if (!profilePic.isEmpty()) {
        // employee.setProfilePic(profilePic.getOriginalFilename());
        // }
        // Employee saveEmployee = service.saveEmployee(employee);
        // if (saveEmployee != null && profilePic.isEmpty()) {
        // try {
        // File savFile = new ClassPathResource("static/images").getFile();
        // Path path = Paths.get(savFile.getAbsolutePath() + File.separator +
        // profilePic.getOriginalFilename());
        // Files.copy(profilePic.getInputStream(), path,
        // StandardCopyOption.REPLACE_EXISTING);

        // } catch (Exception ee) {

        // ee.printStackTrace();
        // }
        // } else {
        // service.saveEmployee(saveEmployee);
        // }

        // if (!profilePic.isEmpty()) {

        // try {
        // String fileName = profilePic.getOriginalFilename();
        // employee.setProfilePic(fileName);
        // System.out.println("Name file is :" + fileName);
        // File saveDirectory = new ClassPathResource("static/images").getFile();
        // Path path = Paths
        // .get(saveDirectory.getAbsolutePath() + File.separator + fileName);
        // Files.copy(profilePic.getInputStream(), path,
        // StandardCopyOption.REPLACE_EXISTING);
        // } catch (Exception ee) {
        // ee.printStackTrace();
        // redirectAttributes.addFlashAttribute("message", "File upload failed.");
        // }
        // }
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setAddress(address);
        employee.setPassword(password);
        employee.setDob(dob);
        System.out.println("Image Name is :" + profilePic.getOriginalFilename());
        employee.setProfilePic(profilePic.getOriginalFilename());
        Employee uploadImage = service.saveEmployee(employee);
        if (uploadImage != null) {
            try {
                File saveFile = new File("src/main/resources/static/images");
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + profilePic.getOriginalFilename());
                Files.copy(profilePic.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

        return "redirect:/emp_save";

    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/submit")
    public String edit(@RequestParam int id, @RequestParam String name, @RequestParam String email,
            @RequestParam String address,
            @RequestParam String gender, @RequestParam String password, @RequestParam LocalDate dob) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setAddress(address);
        employee.setPassword(password);
        employee.setDob(dob);

        service.updateEmployee(employee, id);
        return "redirect:/edit_emp";
    }

    @PostMapping("/submitId")
    public String postMethodName(@RequestParam int id) {

        service.deleteId(id);

        return "redirect:/index";
    }

}
