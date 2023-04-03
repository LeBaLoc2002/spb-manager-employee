package com.example.managerEmployees.controller.api;

import com.example.managerEmployees.model.dto.position.PositionDTO;
import com.example.managerEmployees.service.position.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/positions")
public class PositionAPI {
    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping
    public ResponseEntity<?> getAllEmployee() {
        List<PositionDTO>employeeDTOList = positionService.getAllPositionDTO();

        if(employeeDTOList.size() == 0 ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employeeDTOList,HttpStatus.OK);
    }
}
