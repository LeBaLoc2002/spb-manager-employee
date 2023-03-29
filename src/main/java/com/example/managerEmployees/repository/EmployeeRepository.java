package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.dto.employee.EmployeeDTO;
import com.example.managerEmployees.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT new com.example.managerEmployees.model.dto.employee.EmployeeDTO (" +
                "el.id, " +
                "el.name, " +
                "el.salary, " +
                "el.experience, " +
                "el.dateOfJoining, " +
                "el.phone, " +
                "el.role, " +
                "el.locationRegion," +
                "el.employeeAvatar, " +
                "el.department" +
            ") " +
            "FROM Employee AS el " +
            "WHERE el.deleted = false"
        )
    List<EmployeeDTO> getAllEmployeeDTO();

    @Query("SELECT new com.example.managerEmployees.model.dto.employee.EmployeeDTO (" +
            "el.id, " +
            "el.name, " +
            "el.salary, " +
            "el.experience, " +
            "el.dateOfJoining, " +
            "el.phone, " +
            "el.role, " +
            "el.locationRegion," +
            "el.employeeAvatar, " +
            "el.department" +
            ") " +
            "FROM Employee AS el " +
            "WHERE el.id = ?1"
    )
    Optional<EmployeeDTO> findEmployeeById(Long employeeId);
    @Modifying
    @Query("update Employee AS el SET el.deleted = true WHERE el.id = :employeeId")
    void softDelete(@Param("employeeId") Long employeeId);

    @Query("SELECT e from Employee e")
    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT new com.example.managerEmployees.model.dto.employee.EmployeeDTO (" +
                "el.id, " +
                "el.name, " +
                "el.salary, " +
                "el.experience, " +
                "el.dateOfJoining, " +
                "el.phone, " +
                "el.role, " +
                "el.locationRegion," +
                "el.employeeAvatar, " +
                "el.department" +
            ") "+
            "FROM Employee as el " +
            "join Department as dp " +
            "ON el.department = dp " +
            "WHERE el.deleted = false " +
            "AND (el.name LIKE :keyword " +
            "OR el.department.name LIKE :keyword)"
    )
    List<EmployeeDTO> finByKeyword(@Param("keyword") String keyword) ;

}
