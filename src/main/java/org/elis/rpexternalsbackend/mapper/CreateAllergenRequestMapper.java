package org.elis.rpexternalsbackend.mapper;

import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.model.Allergen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateAllergenRequestMapper {
    CreateAllergenRequestMapper INSTANCE = Mappers.getMapper(CreateAllergenRequestMapper.class);

    Allergen convert(CreateAllergenRequestDTO createAllergenRequestDTO);
}
