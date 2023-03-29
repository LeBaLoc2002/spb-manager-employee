package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.dto.employee.EmployeeFillterDTO;
import com.example.managerEmployees.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeFilterRepository extends JpaRepository<Employee, String> , JpaSpecificationExecutor<Employee> {
        default Page<Employee> findAllByFilters(EmployeeFillterDTO employeeFillterDTO , Pageable pageable) {
            return findAll((root, criteriaQuery, criteriaBuilder) -> {

                List<Predicate> predicates = new ArrayList<>();
                if (employeeFillterDTO.getDepartmentId() != null) {
                    Predicate predicateDepartment = criteriaBuilder.equal(root.get("department").get("id"), employeeFillterDTO.getDepartmentId());
                    predicates.add(predicateDepartment);
                }
                if (employeeFillterDTO.getSalary() != null) {
                    Predicate predicateSalary = criteriaBuilder.equal(root.get("salary"), employeeFillterDTO.getSalary());
                    predicates.add(predicateSalary);
                }
                if (employeeFillterDTO.getExperience() != null) {
                    Predicate predicateExperience = criteriaBuilder.equal(root.get("experience") , employeeFillterDTO.getExperience());
                    predicates.add(predicateExperience);
                }
                LocalDate localDateedateOfJoining = LocalDate.parse(employeeFillterDTO.getDateOfJoining());
                Predicate createdAtPredicate = criteriaBuilder.conjunction();
                Path<Date> createdAtPath = root.get("dateOfJoining");
                if (localDateedateOfJoining != null) {
                    createdAtPredicate = criteriaBuilder.equal(createdAtPath, localDateedateOfJoining);
                }
                predicates.add(createdAtPredicate);
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable);
        }
}
