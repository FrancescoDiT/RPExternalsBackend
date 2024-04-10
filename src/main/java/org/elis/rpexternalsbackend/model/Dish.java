package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageLink;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    @ManyToMany(mappedBy = "dishes")
    private List<Ingredient> ingredients;
    //TODO change cascadeType
    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinTable(name ="dish_menu", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name =  "menu_id"))
    private List<Menu> menus;
}
