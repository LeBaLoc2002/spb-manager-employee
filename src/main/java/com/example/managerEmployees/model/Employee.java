package com.example.managerEmployees.model;

import com.example.managerEmployees.model.dto.employee.EmployeeDTO;
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
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "employee_name")
    private String name ;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal salary ;
    private String experience;

    private LocalDate dateOfJoining;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;


    @OneToOne
    @JoinColumn(name = "location_region_id",  referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @OneToOne
    @JoinColumn(name = "employee_avatar_id", referencedColumnName = "id", nullable = false)
    private EmployeeAvatar employeeAvatar;

    @OneToOne
    @JoinColumn(name = "department_id",  referencedColumnName = "id", nullable = false)
    private Department department;

    public EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO()
                .setId(id)
                .setName(name)
                .setSalary(salary)
                .setExperience(experience)
                .setDateOfJoining(String.valueOf(dateOfJoining))
                .setPhone(phone)
                .setEmployeeAvatar(employeeAvatar.toEmployeeAvatarDTO())
                .setDepartment(department.toDepartmentDTO())
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setRole(role.toRoleDTO());
    }
}