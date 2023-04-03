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
                "emp.id, " +
                "emp.fullName, " +
                "emp.salary, " +
                "emp.experience, " +
                "emp.dateOfJoining, " +
                "emp.phone, " +
                "emp.position, " +
                "emp.locationRegion," +
                "emp.employeeAvatar, " +
                "emp.department" +
            ") " +
            "FROM Employee AS emp " +
            "WHERE emp.deleted = false"
        )
    List<EmployeeDTO> getAllEmployeeDTO();

    @Query("SELECT new com.example.managerEmployees.model.dto.employee.EmployeeDTO (" +
                "emp.id, " +
                "emp.fullName, " +
                "emp.salary, " +
                "emp.experience, " +
                "emp.dateOfJoining, " +
                "emp.phone, " +
                "emp.position, " +
                "emp.locationRegion," +
                "emp.employeeAvatar, " +
                "emp.department" +
            ") " +
            "FROM Employee AS emp " +
            "WHERE emp.id = ?1"
    )
    Optional<EmployeeDTO> findEmployeeById(Long employeeId);
    
    @Modifying
    @Query("update Employee AS emp SET emp.deleted = true WHERE emp.id = :employeeId")
    void softDelete(@Param("employeeId") Long employeeId);

    @Query("SELECT emp from Employee AS emp")
    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT new com.example.managerEmployees.model.dto.employee.EmployeeDTO (" +
                "emp.id, " +
                "emp.fullName, " +
                "emp.salary, " +
                "emp.experience, " +
                "emp.dateOfJoining, " +
                "emp.phone, " +
                "emp.position, " +
                "emp.locationRegion," +
                "emp.employeeAvatar, " +
                "emp.department" +
            ") "+
            "FROM Employee AS emp " +
            "JOIN Department AS dp " +
            "ON emp.department = dp " +
            "WHERE emp.deleted = false " +
            "AND (emp.fullName LIKE :keyword " +
            "OR emp.department.name LIKE :keyword)"
    )
    List<EmployeeDTO> finByKeyword(@Param("keyword") String keyword) ;

    Boolean existsEmployeeByFullNameAndDeletedIsFalse(String fullName);

    Boolean existsEmployeeByFullNameAndIdNotAndDeletedIsFalse(String name , Long id);

}
