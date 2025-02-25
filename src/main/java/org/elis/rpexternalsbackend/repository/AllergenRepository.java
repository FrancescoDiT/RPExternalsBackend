package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository  extends JpaRepository<Allergen, Long> {
    Allergen findByName(String name);
}
