package com.example.managerEmployees.model;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;

    public DepartmentDTO toDepartmentDTO() {
        return new DepartmentDTO()
                .setId(id)
                .setName(name);
    }
}
