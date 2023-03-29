package com.example.managerEmployees.controller.controller;

import com.example.managerEmployees.exception.DataInputException;
import com.example.managerEmployees.model.Employee;
import com.example.managerEmployees.service.Employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("cp/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

//    @GetMapping
//    public String showIndexPage(Model model) {
////        employeeService.getInfo(model);
//
//        return "/employee/index";
//    }
    @GetMapping
    public String showIndexPage() {
//        employeeService.getInfo(model);

        return "/employee/index";
    }

//    @GetMapping("/create")
//    public String showCreatePage(Model model) {
////        employeeService.getInfo(model);
//        return "/employee/Create";
//    }

    @GetMapping("/create")
    public String showCreatePage() {
//        employeeService.getInfo(model);
        return "create";
    }

    @GetMapping("/update/{employeeId}")
    public String showUpdatePage(@PathVariable Long employeeId) {

        Optional<Employee> employee = employeeService.findById(employeeId);

        if (!employee.isPresent()) {
            throw new DataInputException("ID nhân viên không hợp lệ");
        }

//        model.addAttribute("employeeId", employeeId);

//        employeeService.getInfo(model);

        return "update";
    }

//    @GetMapping("/update/{employeeId}")
//    public String showUpdatePage(@PathVariable Long employeeId, Model model) {
//
//        Optional<Employee> employee = employeeService.findById(employeeId);
//
//        if (!employee.isPresent()) {
//            throw new DataInputException("ID nhân viên không hợp lệ");
//        }
//
//        model.addAttribute("employeeId", employeeId);
//
////        employeeService.getInfo(model);
//
//        return "/employee/Update";
//    }
}
