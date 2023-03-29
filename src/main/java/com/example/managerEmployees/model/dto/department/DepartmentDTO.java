package com.example.managerEmployees.model.dto.department;

import com.example.managerEmployees.model.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDTO {
    private Long id;

    private String name ;

    public Department toDepartmentDTO (){
        return new Department()
                .setId(id)
                .setName(name);
    }
}
