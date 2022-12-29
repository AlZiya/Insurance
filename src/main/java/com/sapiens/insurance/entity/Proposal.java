package com.sapiens.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity(name = "proposal")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private int gender;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private boolean tobaccoConsumption;
    private double annualIncome;
    @Max(30000000)
    @Min(0)
    private double lifeCoverAmount;
    @Max(100)
    @Min(1)
    private int age;
    private int status;
    private String submittedBy;
    private String policyNumber;
    private double premium;
    private boolean topUp;
    private boolean accidentalCover;
    private double accidentalDeathCover;
    private boolean care;
    private double comprehensiveCare;

}
//For term insurance, obtain the input like First name & Last name, DOB, E-Mail id, mobile number, gender, do you consume tobacco?
// annual income, life cover amount(up to 3 crores), life cover up to age (range of age can be up to 100 years)