package org.elis.rpexternalsbackend.mapper;

import org.elis.rpexternalsbackend.dto.request.CreateIngredientRequestDTO;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateIngredientRequestMapper {
    CreateIngredientRequestMapper INSTANCE = Mappers.getMapper(CreateIngredientRequestMapper.class);

    Ingredient convert(CreateIngredientRequestDTO createIngredientRequestDTO);
}
