package org.elis.rpexternalsbackend.controller;

import jakarta.validation.Valid;
import org.elis.rpexternalsbackend.dto.request.CreateAllergenRequestDTO;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.service.definition.AllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllergenController {
    @Autowired
    private AllergenService allergenService;

    @PostMapping("/all/allergens/create")
    public ResponseEntity<Void> createAllergen(@RequestBody @Valid CreateAllergenRequestDTO createAllergenRequestDTO){
       Allergen allergen = allergenService.createAllergen(createAllergenRequestDTO);
        if(allergen != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/all/allergens/createList")
    public ResponseEntity<Void> createAllergenList(@RequestBody @Valid List<CreateAllergenRequestDTO> createAllergenRequestDTOS){
        List<Allergen> allergens = allergenService.createAllergenList(createAllergenRequestDTOS);
        if(!allergens.isEmpty()) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @GetMapping("/all/allergens/readAll")
    public ResponseEntity<List<Allergen>> readAllAllergens(){
        List<Allergen> allergens = allergenService.readAllAllergen();
        return ResponseEntity.ok(allergens);
    }
}
