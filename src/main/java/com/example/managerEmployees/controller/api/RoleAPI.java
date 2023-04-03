package com.example.managerEmployees.controller.api;

import com.example.managerEmployees.model.dto.role.RoleDTO;
import com.example.managerEmployees.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleAPI {
    @Autowired
    private RoleServiceImpl roleService;
    @GetMapping
    public ResponseEntity<?> getAllEmployee() {
        List<RoleDTO>employeeDTOList = roleService.getAllRoleDTO();
        if(employeeDTOList.size() == 0 ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeDTOList,HttpStatus.OK);
    }
}
