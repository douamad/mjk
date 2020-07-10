package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.ConclusionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Conclusion} and its DTO {@link ConclusionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConclusionMapper extends EntityMapper<ConclusionDTO, Conclusion> {
    @Mapping(target = "creances", ignore = true)
    @Mapping(target = "removeCreances", ignore = true)
    @Mapping(target = "saisines", ignore = true)
    @Mapping(target = "removeSaisines", ignore = true)
    Conclusion toEntity(ConclusionDTO conclusionDTO);

    default Conclusion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conclusion conclusion = new Conclusion();
        conclusion.setId(id);
        return conclusion;
    }
}
