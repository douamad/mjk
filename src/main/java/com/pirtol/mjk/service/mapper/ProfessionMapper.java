package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.ProfessionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profession} and its DTO {@link ProfessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfessionMapper extends EntityMapper<ProfessionDTO, Profession> {
    @Mapping(target = "professions", ignore = true)
    @Mapping(target = "removeProfession", ignore = true)
    Profession toEntity(ProfessionDTO professionDTO);

    default Profession fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profession profession = new Profession();
        profession.setId(id);
        return profession;
    }
}
