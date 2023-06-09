package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.EmployeeAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAvatarRepository extends JpaRepository<EmployeeAvatar, String> {
}
