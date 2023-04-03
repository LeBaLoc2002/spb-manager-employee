package com.example.managerEmployees.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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