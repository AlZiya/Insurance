package com.sapiens.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 2, max = 250)
    private String firstName;
    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 2, max = 250)
    private String lastName;
    @NotEmpty(message = "User's email cannot be empty.")
    @Id
    private String email;
    private long mobile;
    private int gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String role;
    private String password;

}
