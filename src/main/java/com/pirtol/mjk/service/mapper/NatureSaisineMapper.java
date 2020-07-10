package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.NatureSaisineDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NatureSaisine} and its DTO {@link NatureSaisineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NatureSaisineMapper extends EntityMapper<NatureSaisineDTO, NatureSaisine> {
    @Mapping(target = "saisines", ignore = true)
    @Mapping(target = "removeSaisines", ignore = true)
    NatureSaisine toEntity(NatureSaisineDTO natureSaisineDTO);

    default NatureSaisine fromId(Long id) {
        if (id == null) {
            return null;
        }
        NatureSaisine natureSaisine = new NatureSaisine();
        natureSaisine.setId(id);
        return natureSaisine;
    }
}
