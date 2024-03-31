package org.inptdeveloppers.restaurantservice.controllers;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.DTOS.MenuDTO;
import org.inptdeveloppers.restaurantservice.DTOS.MenucreationDTO;
import org.inptdeveloppers.restaurantservice.services.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MenuController {
    private final MenuService menuService;
    @GetMapping("/restaurants/menu/{id}")
    public ResponseEntity<MenuDTO> getmenu( @PathVariable  Long id,  @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size){
        MenuDTO menuDTO=menuService.getmenu(id,page,size);
        return  new ResponseEntity<MenuDTO>(menuDTO, HttpStatus.OK);

    }
    @PostMapping("/restaurants/menu")
public ResponseEntity saveMenu(@RequestBody MenucreationDTO menucreationDTO){
        menuService.createMenu(menucreationDTO);
        return new ResponseEntity(HttpStatus.CREATED);

    }

}
