package com.pirtol.mjk.service.mapper;

import com.pirtol.mjk.domain.*;
import com.pirtol.mjk.service.dto.SaisineDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Saisine} and its DTO {@link SaisineDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        ObjetSaisineMapper.class,
        NatureSaisineMapper.class,
        OrigineSaisineMapper.class,
        ConclusionMapper.class,
        MaisonMapper.class,
        RequerantMapper.class,
    }
)
public interface SaisineMapper extends EntityMapper<SaisineDTO, Saisine> {
    @Mapping(source = "objet.id", target = "objetId")
    @Mapping(source = "nature.id", target = "natureId")
    @Mapping(source = "origine.id", target = "origineId")
    @Mapping(source = "conclusion.id", target = "conclusionId")
    @Mapping(source = "maison.id", target = "maisonId")
    @Mapping(source = "demandeur.id", target = "demandeurId")
    @Mapping(source = "defendeur.id", target = "defendeurId")
    SaisineDTO toDto(Saisine saisine);

    @Mapping(source = "objetId", target = "objet")
    @Mapping(source = "natureId", target = "nature")
    @Mapping(source = "origineId", target = "origine")
    @Mapping(source = "conclusionId", target = "conclusion")
    @Mapping(source = "maisonId", target = "maison")
    @Mapping(source = "demandeurId", target = "demandeur")
    @Mapping(source = "defendeurId", target = "defendeur")
    Saisine toEntity(SaisineDTO saisineDTO);

    default Saisine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Saisine saisine = new Saisine();
        saisine.setId(id);
        return saisine;
    }
}
