package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.model.Allergen;

import java.util.List;

public interface AllergenService {
    Allergen createAllergen(CreateAllergenRequestDTO createAllergenRequestDTO);
    List<Allergen> readAllAllergen();
    void updateAllergen();
    void deleteAllergen();
    Boolean isAllergenPresent(String allergenName);
}
