package com.example.managerEmployees.service.position;

import com.example.managerEmployees.model.dto.position.PositionDTO;
import com.example.managerEmployees.model.Position;
import com.example.managerEmployees.model.enums.EnumPosition;
import com.example.managerEmployees.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private PositionRepository positionRepository;
    @Override
    public List<Position> findAll() {
        return null;
    }

    @Override
    public Position getById(Long id) {
        return positionRepository.getById(id);
    }

    @Override
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position getById(String id) {
        return null;
    }

    @Override
    public Optional<Position> findById(String id) {
        return Optional.empty();
    }


    @Override
    public List<PositionDTO> getAllPositionDTO() {
        return positionRepository.getAllPositionDTO();
    }

    @Override
    public Optional<Position> findByCode(EnumPosition code) {
        return positionRepository.findByCode(code);
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public void remove(Long id) {

    }
}
