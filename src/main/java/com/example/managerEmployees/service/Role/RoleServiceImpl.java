package com.example.managerEmployees.service.Role;

import com.example.managerEmployees.model.dto.role.RoleDTO;
import com.example.managerEmployees.model.Role;
import com.example.managerEmployees.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getById(String id) {
        return null;
    }

    @Override
    public Optional<Role> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<RoleDTO> getAllRoleDTO() {
        return roleRepository.getAllRoleDTO();
    }
}
