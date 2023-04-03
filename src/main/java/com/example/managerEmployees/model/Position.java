package com.example.managerEmployees.model;

import com.example.managerEmployees.model.dto.position.PositionDTO;
import com.example.managerEmployees.model.enums.EnumPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EnumPosition code;


    @OneToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    public PositionDTO toPositionDTO() {
        return new PositionDTO()
                .setId(id)
                .setCode(code.getValue());
    }
}
