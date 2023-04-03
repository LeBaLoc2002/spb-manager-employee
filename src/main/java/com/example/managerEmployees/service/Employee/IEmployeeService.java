package com.example.managerEmployees.service.Employee;

import com.example.managerEmployees.model.dto.employee.EmployeeCreateDTO;
import com.example.managerEmployees.model.dto.employee.EmployeeDTO;
import com.example.managerEmployees.model.dto.employee.EmployeeFillterDTO;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.model.Employee;
import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.model.Role;
import com.example.managerEmployees.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService extends IGeneralService<Employee> {


    Page<EmployeeDTO> findAllByFilters(EmployeeFillterDTO employeeFillterDTO, Pageable pageable);

    List<EmployeeDTO> getAllEmployeeDTO();

    Employee createEmployee(EmployeeCreateDTO employeeCreateDTO, LocationRegion locationRegion, Role role , Department department) throws ParseException;

    Employee updateWithAvatar(Employee employee, MultipartFile file) throws IOException;
    Employee updateNoAvatar(Employee employee) throws ParseException;
    Employee saveLocationRegion(Employee employee);

    void softDelete (Long employeeId);

    Page<Employee> findAll(Pageable pageable);

    List<EmployeeDTO> finByKeyword(@Param("keyword")String keyword) ;

    Optional<EmployeeDTO> findEmployeeById(Long employeeId);

    EmployeeDTO getInfo(Model model);

    Boolean existsEmployeeByNameAndDeletedIsFalse(String name);

    Boolean existsEmployeeByNameAndIdNotAndDeletedIsFalse(String name , Long id);

}
