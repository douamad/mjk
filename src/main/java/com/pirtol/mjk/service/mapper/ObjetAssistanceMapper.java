package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.ObjetAssistanceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ObjetAssistance} and its DTO {@link ObjetAssistanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ObjetAssistanceMapper extends EntityMapper<ObjetAssistanceDTO, ObjetAssistance> {
    @Mapping(target = "objets", ignore = true)
    @Mapping(target = "removeObjet", ignore = true)
    ObjetAssistance toEntity(ObjetAssistanceDTO objetAssistanceDTO);

    default ObjetAssistance fromId(Long id) {
        if (id == null) {
            return null;
        }
        ObjetAssistance objetAssistance = new ObjetAssistance();
        objetAssistance.setId(id);
        return objetAssistance;
    }
}
