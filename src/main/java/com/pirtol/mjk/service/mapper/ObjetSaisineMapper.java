package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.ObjetSaisineDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ObjetSaisine} and its DTO {@link ObjetSaisineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ObjetSaisineMapper extends EntityMapper<ObjetSaisineDTO, ObjetSaisine> {
    @Mapping(target = "saisines", ignore = true)
    @Mapping(target = "removeSaisines", ignore = true)
    ObjetSaisine toEntity(ObjetSaisineDTO objetSaisineDTO);

    default ObjetSaisine fromId(Long id) {
        if (id == null) {
            return null;
        }
        ObjetSaisine objetSaisine = new ObjetSaisine();
        objetSaisine.setId(id);
        return objetSaisine;
    }
}
