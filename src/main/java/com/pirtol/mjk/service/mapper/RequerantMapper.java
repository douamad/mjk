package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.RequerantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Requerant} and its DTO {@link RequerantDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProfessionMapper.class, EthnieMapper.class })
public interface RequerantMapper extends EntityMapper<RequerantDTO, Requerant> {
    @Mapping(source = "profession.id", target = "professionId")
    @Mapping(source = "ethnie.id", target = "ethnieId")
    RequerantDTO toDto(Requerant requerant);

    @Mapping(target = "demandeAssistances", ignore = true)
    @Mapping(target = "removeDemandeAssistance", ignore = true)
    @Mapping(target = "defenseAssistances", ignore = true)
    @Mapping(target = "removeDefenseAssistance", ignore = true)
    @Mapping(target = "demandeCreances", ignore = true)
    @Mapping(target = "removeDemandeCreances", ignore = true)
    @Mapping(target = "defenseCreances", ignore = true)
    @Mapping(target = "removeDefenseCreances", ignore = true)
    @Mapping(target = "demandeSaisines", ignore = true)
    @Mapping(target = "removeDemandeSaisines", ignore = true)
    @Mapping(target = "defenseSaisines", ignore = true)
    @Mapping(target = "removeDefenseSaisines", ignore = true)
    @Mapping(source = "professionId", target = "profession")
    @Mapping(source = "ethnieId", target = "ethnie")
    Requerant toEntity(RequerantDTO requerantDTO);

    default Requerant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Requerant requerant = new Requerant();
        requerant.setId(id);
        return requerant;
    }
}
