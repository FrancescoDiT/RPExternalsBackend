package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AllergenService {
    //CREATEs
    Allergen createAllergen(CreateAllergenRequestDTO createAllergenRequestDTO);
    List<Allergen> createAllergenList(List<CreateAllergenRequestDTO> createAllergenRequestDTOList);
    //READs
    List<Allergen> readAllAllergen();
    void updateAllergen();
    void deleteAllergen();
    Boolean isAllergenPresent(String allergenName);
}
