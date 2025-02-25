package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.repository.AllergenRepository;
import org.elis.rpexternalsbackend.service.definition.AllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AllergenServiceImpl implements AllergenService {
    @Autowired
    private AllergenRepository allergenRepository;

    @Override
    public Boolean isAllergenPresent(String allergenName) {
        String correctName = allergenName.trim();
        Allergen allergen = allergenRepository.findByName(correctName);
        return allergen != null;
    }

    @Override
    public Allergen createAllergen(CreateAllergenRequestDTO createAllergenRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        if(isAllergenPresent(createAllergenRequestDTO.getName())){
            errors.put("AllergenAlreadyOnDb", "Allergen already exists");
        }

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        Allergen allergen = Allergen.builder()
                                    .name(createAllergenRequestDTO.getName())
                                    .imageLink(createAllergenRequestDTO.getImageLink())
                                    .description(createAllergenRequestDTO.getDescription())
                                    .build();
        return allergenRepository.save(allergen);
    }

    @Override
    public List<Allergen> readAllAllergen() {
        return allergenRepository.findAll();
    }

    @Override
    public void updateAllergen() {

    }

    @Override
    public void deleteAllergen() {

    }




}
