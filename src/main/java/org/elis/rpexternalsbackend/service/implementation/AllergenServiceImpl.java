package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.mapper.CreateAllergenRequestMapper;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.repository.AllergenRepository;
import org.elis.rpexternalsbackend.service.definition.AllergenService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AllergenServiceImpl implements AllergenService {
    @Autowired
    private AllergenRepository allergenRepository;

    @Override
    public Allergen createAllergen(CreateAllergenRequestDTO createAllergenRequestDTO) {
        Allergen allergen = CreateAllergenRequestMapper.INSTANCE.convert(createAllergenRequestDTO);
        return allergenRepository.save(allergen);
    }

    @Override
    public List<Allergen> createAllergenList(List<CreateAllergenRequestDTO> createAllergenRequestDTOS) {
        List<Allergen> allergens = createAllergenRequestDTOS.stream().map(CreateAllergenRequestMapper.INSTANCE::convert).toList();
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
