package com.example.managerEmployees.service.ImageEmployee;

import com.example.managerEmployees.model.EmployeeAvatar;
import com.example.managerEmployees.repository.ImageEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeAvatarImpl implements IEmployeeAvatarService {

    @Autowired
    private ImageEmployeeRepository imageEmployeeRepository;
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
        return imageEmployeeRepository.getById(id);
    }

    @Override
    public Optional<EmployeeAvatar> findById(String id) {
        return imageEmployeeRepository.findById(id);
    }


    @Override
    public EmployeeAvatar save(EmployeeAvatar employeeAvatar) {
        return imageEmployeeRepository.save(employeeAvatar);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void delete(String id) {
        imageEmployeeRepository.deleteById(id);
    }
}
