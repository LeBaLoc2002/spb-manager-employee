package com.example.managerEmployees.model.dto.employee;

import com.example.managerEmployees.AppUtils.ValidateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeFillterDTO {


    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateOfJoining;
    private Long departmentId;

    private String experience;
    private String salary;
    private Long draw;
    private Integer length;
    private Integer start;

}