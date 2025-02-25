package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.elis.rpexternalsbackend.model.value.DishType;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageLink;
    @Column
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    @ManyToMany(mappedBy = "dishes")
    private List<Ingredient> ingredients;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="dish_menu", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name =  "menu_id"))
    private List<Menu> menus;
}
