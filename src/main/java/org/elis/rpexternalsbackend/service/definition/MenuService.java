package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateMenuRequestDTO;
import org.elis.rpexternalsbackend.model.Menu;

import java.util.List;

public interface MenuService {
     Menu createMenu(CreateMenuRequestDTO createMenuRequestDTO);
     List<Menu> readAllMenus();
     void updateMenu();
     void deleteMenu();
     Boolean isValidDate(String date);

}
