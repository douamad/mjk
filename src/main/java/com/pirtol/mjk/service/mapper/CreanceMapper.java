package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.CreanceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Creance} and its DTO {@link CreanceDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrigineSaisineMapper.class, ConclusionMapper.class, MaisonMapper.class, RequerantMapper.class })
public interface CreanceMapper extends EntityMapper<CreanceDTO, Creance> {
    @Mapping(source = "origine.id", target = "origineId")
    @Mapping(source = "conclusions.id", target = "conclusionsId")
    @Mapping(source = "maison.id", target = "maisonId")
    @Mapping(source = "demandeur.id", target = "demandeurId")
    @Mapping(source = "defendeur.id", target = "defendeurId")
    CreanceDTO toDto(Creance creance);

    @Mapping(source = "origineId", target = "origine")
    @Mapping(source = "conclusionsId", target = "conclusions")
    @Mapping(source = "maisonId", target = "maison")
    @Mapping(source = "demandeurId", target = "demandeur")
    @Mapping(source = "defendeurId", target = "defendeur")
    Creance toEntity(CreanceDTO creanceDTO);

    default Creance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Creance creance = new Creance();
        creance.setId(id);
        return creance;
    }
}
