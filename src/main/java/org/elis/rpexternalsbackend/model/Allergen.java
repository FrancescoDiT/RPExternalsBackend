package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "allergen")
public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String imageLink;
    @Column(nullable = false)
    private String description;

    //
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="allergen_ingredient", joinColumns = @JoinColumn(name = "allergen_id"), inverseJoinColumns = @JoinColumn(name =  "ingredient_id"))
    private List<Ingredient> ingredients;
}
