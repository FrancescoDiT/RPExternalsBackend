package org.elis.rpexternalsbackend.controller;

import jakarta.validation.Valid;
import org.elis.rpexternalsbackend.dto.request.CreateMenuRequestDTO;
import org.elis.rpexternalsbackend.model.Menu;
import org.elis.rpexternalsbackend.service.definition.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @PostMapping("/all/menus/create")
    public ResponseEntity<Void> createMenu(@RequestBody @Valid CreateMenuRequestDTO createMenuRequestDTO){
        Menu menu = menuService.createMenu(createMenuRequestDTO);
        if(menu != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all/menus/readAll")
    public ResponseEntity<List<Menu>> readAllMenus(){
        List<Menu> menus = menuService.readAllMenus();
        return ResponseEntity.ok(menus);
    }
}
