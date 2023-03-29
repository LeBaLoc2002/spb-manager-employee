package com.example.managerEmployees.service.Role;

import com.example.managerEmployees.model.dto.role.RoleDTO;
import com.example.managerEmployees.model.Role;
import com.example.managerEmployees.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    List<RoleDTO> getAllRoleDTO();
}
