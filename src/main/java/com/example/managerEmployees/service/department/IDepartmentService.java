package com.example.managerEmployees.service.department;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.service.IGeneralService;

import java.util.List;

public interface IDepartmentService extends IGeneralService<Department> {
    List<DepartmentDTO> findAllDepartment();
}
