package com.example.managerEmployees.service.imageEmployee;

import com.example.managerEmployees.model.EmployeeAvatar;
import com.example.managerEmployees.repository.EmployeeAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeAvatarServiceImpl implements IEmployeeAvatarService {

    @Autowired
    private EmployeeAvatarRepository employeeAvatarRepository;
    @Override
    public List<EmployeeAvatar> findAll() {
        return null;
    }

    @Override
    public EmployeeAvatar getById(Long id) {
        return null;
    }

    @Override
    public Optional<EmployeeAvatar> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public EmployeeAvatar getById(String id) {
        return employeeAvatarRepository.getById(id);
    }

    @Override
    public Optional<EmployeeAvatar> findById(String id) {
        return employeeAvatarRepository.findById(id);
    }


    @Override
    public EmployeeAvatar save(EmployeeAvatar employeeAvatar) {
        return employeeAvatarRepository.save(employeeAvatar);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void delete(String id) {
        employeeAvatarRepository.deleteById(id);
    }
}
