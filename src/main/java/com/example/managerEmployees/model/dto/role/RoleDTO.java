package com.example.managerEmployees.model.dto.role;

import com.example.managerEmployees.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleDTO {
    private Long id;

    private String code;

    public Role toRole () {
        return new Role()
                .setId(id)
                .setCode(code);
    }
}
