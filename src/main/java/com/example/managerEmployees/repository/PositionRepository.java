package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.Position;
import com.example.managerEmployees.model.dto.position.PositionDTO;
import com.example.managerEmployees.model.enums.EnumPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("SELECT NEW com.example.managerEmployees.model.dto.position.PositionDTO(" +
                "pst.id, " +
                "pst.code" +
            ") " +
            "FROM Position AS pst "
    )
    List<PositionDTO> getAllPositionDTO();

    Optional<Position> findByCode(EnumPosition code);
}
