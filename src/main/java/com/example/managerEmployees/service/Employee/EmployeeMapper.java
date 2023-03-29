package com.example.managerEmployees.service.Employee;

import com.example.managerEmployees.model.dto.employee.EmployeeDTO;
import com.example.managerEmployees.model.Employee;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper{

    public EmployeeDTO toEmployeeDTO (Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setExperience(employee.getExperience());
        employeeDTO.setDateOfJoining(employeeDTO.getDateOfJoining());
        employeeDTO.setDepartment(employee.getDepartment().toDepartmentDTO());
        return employeeDTO;
    }

}
