package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.AssistanceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Assistance} and its DTO {@link AssistanceDTO}.
 */
@Mapper(componentModel = "spring", uses = { ObjetAssistanceMapper.class, MaisonMapper.class, RequerantMapper.class })
public interface AssistanceMapper extends EntityMapper<AssistanceDTO, Assistance> {
    @Mapping(source = "objetAssistance.id", target = "objetAssistanceId")
    @Mapping(source = "maison.id", target = "maisonId")
    @Mapping(source = "demandeur.id", target = "demandeurId")
    @Mapping(source = "defendeur.id", target = "defendeurId")
    AssistanceDTO toDto(Assistance assistance);

    @Mapping(source = "objetAssistanceId", target = "objetAssistance")
    @Mapping(source = "maisonId", target = "maison")
    @Mapping(source = "demandeurId", target = "demandeur")
    @Mapping(source = "defendeurId", target = "defendeur")
    Assistance toEntity(AssistanceDTO assistanceDTO);

    default Assistance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Assistance assistance = new Assistance();
        assistance.setId(id);
        return assistance;
    }
}
