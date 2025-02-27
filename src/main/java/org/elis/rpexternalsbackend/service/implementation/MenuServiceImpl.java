package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateMenuRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.model.Dish;
import org.elis.rpexternalsbackend.model.Menu;
import org.elis.rpexternalsbackend.repository.DishRepository;
import org.elis.rpexternalsbackend.repository.MenuRepository;
import org.elis.rpexternalsbackend.service.definition.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    public Boolean isValidDate(String dateString) {
        // Definizione del formato "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Parsing della stringa come LocalDateTime
            LocalDateTime.parse(dateString, formatter);
            return true; // La stringa è valida
        } catch (DateTimeParseException e) {
            return false; // La stringa non è valida
        }
    }

    @Override
    public Menu createMenu(CreateMenuRequestDTO createMenuRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        if(!isValidDate(createMenuRequestDTO.getDate())){
            errors.put("DateNotFormatted", "Date is not formatted as yyyy-MM-dd HH:mm:ss");
        }

        LocalDateTime date = LocalDateTime.parse(createMenuRequestDTO.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Long> dishIds = createMenuRequestDTO.getDishIds();
        List<Dish> dishes = dishRepository.findAllById(dishIds);

        if(dishes.isEmpty()){
            errors.put("NoDishesFound", "No dishes found");
        }

        if(dishes.size() != dishIds.size()){
            errors.put("DishNotFound", "Dish not found");
        }

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        Menu menu = Menu.builder()
                .date(date)
                .dishes(dishes)
                .build();
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> readAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public void updateMenu() {

    }

    @Override
    public void deleteMenu() {

    }
}
