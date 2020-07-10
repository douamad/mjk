package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.MaisonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Maison} and its DTO {@link MaisonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaisonMapper extends EntityMapper<MaisonDTO, Maison> {
    @Mapping(target = "assistances", ignore = true)
    @Mapping(target = "removeAssistances", ignore = true)
    @Mapping(target = "creances", ignore = true)
    @Mapping(target = "removeCreances", ignore = true)
    @Mapping(target = "saisines", ignore = true)
    @Mapping(target = "removeSaisines", ignore = true)
    Maison toEntity(MaisonDTO maisonDTO);

    default Maison fromId(Long id) {
        if (id == null) {
            return null;
        }
        Maison maison = new Maison();
        maison.setId(id);
        return maison;
    }
}
