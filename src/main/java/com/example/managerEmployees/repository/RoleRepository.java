package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.dto.role.RoleDTO;
import com.example.managerEmployees.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT NEW com.example.managerEmployees.model.dto.role.RoleDTO(" +
            "r.id, " +
            "r.code" +
            ") " +
            "FROM Role AS r "
    )
    List<RoleDTO> getAllRoleDTO();

    Role findByCode(String code);
}
