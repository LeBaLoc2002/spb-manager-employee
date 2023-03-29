package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.dto.department.DepartmentDTO;
import com.example.managerEmployees.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , Long> {
    @Query("SELECT new com.example.managerEmployees.model.dto.department.DepartmentDTO (" +
            "dpm.id, "+
            "dpm.name" +
            ")" +
            "FROM Department AS dpm"
    )
    List<DepartmentDTO> findAllDepartment();
}
