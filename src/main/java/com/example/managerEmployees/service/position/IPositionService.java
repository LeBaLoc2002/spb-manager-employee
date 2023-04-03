package com.example.managerEmployees.service.position;

import com.example.managerEmployees.model.dto.position.PositionDTO;
import com.example.managerEmployees.model.Position;
import com.example.managerEmployees.model.enums.EnumPosition;
import com.example.managerEmployees.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IPositionService extends IGeneralService<Position> {
    List<PositionDTO> getAllPositionDTO();

    Optional<Position> findByCode(EnumPosition code);
}
