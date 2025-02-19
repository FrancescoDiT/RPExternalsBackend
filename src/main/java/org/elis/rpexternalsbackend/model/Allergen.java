package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "allergen")
public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinTable(name ="allergen_ingredient", joinColumns = @JoinColumn(name = "allergen_id"), inverseJoinColumns = @JoinColumn(name =  "ingredient_id"))
    private List<Ingredient> ingredients;
}
