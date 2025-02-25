package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime date;

    //We will usually use manual query
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<Dish> dishes;
}
