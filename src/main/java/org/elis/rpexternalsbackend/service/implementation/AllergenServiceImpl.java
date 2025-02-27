package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.repository.AllergenRepository;
import org.elis.rpexternalsbackend.service.definition.AllergenService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AllergenServiceImpl implements AllergenService {
    @Autowired
    private AllergenRepository allergenRepository;

    @Override
    public Boolean isAllergenPresent(String allergenName) {
        Allergen allergen = allergenRepository.findByName(allergenName.trim());
        return allergen != null;
    }

    private static Allergen checkAllergen(Map<String, String> errors, CreateAllergenRequestDTO createAllergenRequestDTO){

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        return Allergen.builder()
                .name(createAllergenRequestDTO.getName())
                .imageLink(createAllergenRequestDTO.getImageLink())
                .description(createAllergenRequestDTO.getDescription())
                .build();
    }


    @Override
    public Allergen createAllergen(CreateAllergenRequestDTO createAllergenRequestDTO) {
        Map<String, String> errors = new TreeMap<>();

        if(isAllergenPresent(createAllergenRequestDTO.getName())){
            errors.put("AllergenAlreadyOnDb", "Allergen already exists");
        }

        Allergen allergen = checkAllergen(errors, createAllergenRequestDTO);
        return allergenRepository.save(allergen);
    }

    @Override
    public List<Allergen> createAllergenList(List<CreateAllergenRequestDTO> createAllergenRequestDTOList) {
        Map<String, String> errors = new TreeMap<>();
        List<Allergen> allergens = new ArrayList<>();

        createAllergenRequestDTOList.forEach(createAllergenRequestDTO -> {

                if(isAllergenPresent(createAllergenRequestDTO.getName())){
                    errors.put("AllergenAlreadyOnDb", "Allergen already exists");
                }

                Allergen allergen = checkAllergen(errors, createAllergenRequestDTO);
                allergens.add(allergen);

            });
        return allergenRepository.saveAll(allergens);
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
