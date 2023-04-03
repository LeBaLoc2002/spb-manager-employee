package com.example.managerEmployees.model.dto.position;

import com.example.managerEmployees.model.Position;
import com.example.managerEmployees.model.enums.EnumPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PositionDTO {
    private Long id;

    private String code;

    private String name;

    public PositionDTO(Long id, EnumPosition enumPosition) {
        this.id = id;
        this.code = enumPosition.toString();
        this.name = enumPosition.getValue();
    }

    public Position toPosition () {
        return new Position()
                .setId(id)
                .setCode(EnumPosition.valueOf(code));
    }
}
