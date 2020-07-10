package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.OrigineSaisineDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrigineSaisine} and its DTO {@link OrigineSaisineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrigineSaisineMapper extends EntityMapper<OrigineSaisineDTO, OrigineSaisine> {
    @Mapping(target = "creances", ignore = true)
    @Mapping(target = "removeCreances", ignore = true)
    @Mapping(target = "saisines", ignore = true)
    @Mapping(target = "removeSaisines", ignore = true)
    OrigineSaisine toEntity(OrigineSaisineDTO origineSaisineDTO);

    default OrigineSaisine fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrigineSaisine origineSaisine = new OrigineSaisine();
        origineSaisine.setId(id);
        return origineSaisine;
    }
}
