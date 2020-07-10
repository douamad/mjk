package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.EthnieDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ethnie} and its DTO {@link EthnieDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EthnieMapper extends EntityMapper<EthnieDTO, Ethnie> {
    @Mapping(target = "ethnis", ignore = true)
    @Mapping(target = "removeEthni", ignore = true)
    Ethnie toEntity(EthnieDTO ethnieDTO);

    default Ethnie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ethnie ethnie = new Ethnie();
        ethnie.setId(id);
        return ethnie;
    }
}
