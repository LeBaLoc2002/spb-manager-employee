package com.example.managerEmployees.service.Department;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements IDepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.getById(id);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department getById(String id) {
        return null;
    }

    @Override
    public Optional<Department> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<DepartmentDTO> findAllDepartment() {
        return departmentRepository.findAllDepartment();
    }
}
