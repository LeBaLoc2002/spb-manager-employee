package com.example.managerEmployees.model;

import com.example.managerEmployees.model.dto.employee.EmployeeDTO;
import com.example.managerEmployees.model.enums.EnumPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal salary;

    private String experience;

    private LocalDate dateOfJoining;

    private String phone;

    @Enumerated(EnumType.STRING)
    private EnumPosition position;


    @OneToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @OneToOne
    @JoinColumn(name = "employee_avatar_id", referencedColumnName = "id", nullable = false)
    private EmployeeAvatar employeeAvatar;

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

    public EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO()
                .setId(id)
                .setFullName(fullName)
                .setSalary(salary)
                .setExperience(experience)
                .setDateOfJoining(String.valueOf(dateOfJoining))
                .setPhone(phone)
                .setEmployeeAvatar(employeeAvatar.toEmployeeAvatarDTO())
                .setDepartment(department.toDepartmentDTO())
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setPositionCode(position.toString());
    }
}