package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean frozen;

    @ManyToMany(mappedBy = "ingredients")
    private List<Allergen> allergens;
    //TODO change cascadeType
    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinTable(name ="ingrendient_dish", joinColumns = @JoinColumn(name = "ingredient_id"), inverseJoinColumns = @JoinColumn(name =  "dish_id"))
    private List<Dish> dishes;
}
