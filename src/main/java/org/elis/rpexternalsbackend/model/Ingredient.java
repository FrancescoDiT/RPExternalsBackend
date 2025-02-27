package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String imageLink;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean frozen;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private List<Allergen> allergens;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="ingredient_dish", joinColumns = @JoinColumn(name = "ingredient_id"), inverseJoinColumns = @JoinColumn(name =  "dish_id"))
    private List<Dish> dishes;
}
