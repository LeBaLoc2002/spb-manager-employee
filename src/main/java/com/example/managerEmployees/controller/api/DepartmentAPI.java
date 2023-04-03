package com.example.managerEmployees.controller.api;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import com.example.managerEmployees.service.department.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentAPI {
    @Autowired
    private DepartmentServiceImpl departmentService;

    @GetMapping()
    private ResponseEntity<?> getAllDepartment(){
        List<DepartmentDTO> departmentDTOList = departmentService.findAllDepartment();
        if (departmentDTOList.size() == 0 ) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departmentDTOList, HttpStatus.OK);
    }
}
