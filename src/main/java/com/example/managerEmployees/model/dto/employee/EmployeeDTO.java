package com.example.managerEmployees.model.dto.employee;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import com.example.managerEmployees.model.dto.employeeAvatar.EmployeeAvatarDTO;
import com.example.managerEmployees.model.dto.locationRegion.LocationRegionDTO;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.model.EmployeeAvatar;
import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.model.enums.EnumPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Long id ;
    private String fullName;
    private BigDecimal salary ;
    private String experience;
    private String dateOfJoining;
    private String phone;
    private String positionCode;
    private LocationRegionDTO locationRegion;

    private EmployeeAvatarDTO employeeAvatar;

    private DepartmentDTO department;

    public EmployeeDTO(Long id, String fullName, BigDecimal salary, String experience, LocalDate dateOfJoining, String phone, EnumPosition positionCode, LocationRegion locationRegion, EmployeeAvatar employeeAvatar, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
        this.experience = experience;
        this.dateOfJoining = dateOfJoining.toString();
        this.phone = phone;
        this.positionCode = positionCode.getValue();
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.employeeAvatar = employeeAvatar.toEmployeeAvatarDTO();
        this.department = department.toDepartmentDTO();
    }
}
