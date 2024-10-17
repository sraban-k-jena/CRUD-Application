package com.jt.project_mvc.model;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Nme is Required .")
    private String name;
    @NotEmpty(message = "Email is Required .")
    @Email(message = "Please provide a valid mail .")
    private String email;
    @NotEmpty(message = "Address is Required .")
    private String address;
    private String gender;
    @NotEmpty(message = "Password is required .")
    @Size(min = 6, message = "password should be at least 6 character .")
    private String password;
    private LocalDate dob;
    private String profilePic;
}
