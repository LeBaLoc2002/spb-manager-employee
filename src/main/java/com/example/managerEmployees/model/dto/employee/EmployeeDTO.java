package com.example.managerEmployees.model.dto.employee;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import com.example.managerEmployees.model.dto.employeeAvatar.EmployeeAvatarDTO;
import com.example.managerEmployees.model.dto.locationRegion.LocationRegionDTO;
import com.example.managerEmployees.model.dto.role.RoleDTO;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.model.EmployeeAvatar;
import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.model.Role;
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
    private String name ;
    private BigDecimal salary ;
    private String experience;
    private String dateOfJoining;
    private Long phone;
    private RoleDTO role;
    private LocationRegionDTO locationRegion;

    private EmployeeAvatarDTO emloyeeAvatar;


    private DepartmentDTO department;

    public EmployeeDTO(Long id, String name, BigDecimal salary, String experience, LocalDate dateOfJoining, Long phone, Role role, LocationRegion locationRegion, EmployeeAvatar emloyeeAvatar, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
        this.dateOfJoining = dateOfJoining.toString();
        this.phone = phone;
        this.role = role.toRoleDTO();
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.emloyeeAvatar = emloyeeAvatar.toEmployeeAvatarDTO();
        this.department = department.toDepartmentDTO();
    }
}
