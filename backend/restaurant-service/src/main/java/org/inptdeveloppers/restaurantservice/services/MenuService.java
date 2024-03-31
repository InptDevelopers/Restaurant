package org.inptdeveloppers.restaurantservice.services;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.DTOS.ItemDTO;
import org.inptdeveloppers.restaurantservice.DTOS.MenuDTO;
import org.inptdeveloppers.restaurantservice.DTOS.MenucreationDTO;
import org.inptdeveloppers.restaurantservice.entities.Item;
import org.inptdeveloppers.restaurantservice.entities.Menu;
import org.inptdeveloppers.restaurantservice.mappers.ItemMapper;
import org.inptdeveloppers.restaurantservice.repositories.ItemRepository;
import org.inptdeveloppers.restaurantservice.repositories.MenuRepository;
import org.inptdeveloppers.restaurantservice.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    public MenuDTO getmenu(Long id,int page,int size){
        Page<Item> items;
        if(menuRepository.getByRestaurant(restaurantRepository.findById(id).get())==null){
            return null;

        }
        Menu menu= menuRepository.getByRestaurant( restaurantRepository.findById(id).get());
 items= itemRepository.findAll(PageRequest.of(page, size));
       List<Item> ItemsList = items.getContent();
       List<ItemDTO> itemDTOS=ItemsList.stream()   .map(itemMapper::fromItem)  // Corrected this line
               .collect(Collectors.toList());
       MenuDTO menuDTO=new MenuDTO();
       menuDTO.setItems(itemDTOS);
       menuDTO.setCurrentPage(page);
       menuDTO.setPageSize(items.getSize());
       menuDTO.setTotalPages(items.getTotalPages());
       menuDTO.setId(menu.getId());
       menuDTO.setIdrestaurant(menu.getRestaurant().getId());
       return menuDTO;


    }
    public void createMenu(MenucreationDTO menucreationDTO){
        List<Item> items=new ArrayList<>();
        Menu menu=new Menu();
        menu.setRestaurant(restaurantRepository.findById(menucreationDTO.getIdrestaurant()).get());
        menuRepository.save(menu);
        for(int i=0;i<menucreationDTO.getItems().size();i++){
            Item item=itemMapper.fromItemDTO(menucreationDTO.getItems().get(i));
            item.setMenu(menu);
            itemRepository.save(item);
            items.add(item);

        }
        menu.setItems(items);
        menuRepository.save(menu);



    }
public  List<ItemDTO> getitems(List<Long> itemsId){
        List<ItemDTO> items= new ArrayList<>();
    for (int i = 0; i < itemsId.size(); i++) {
        items.add(itemMapper.fromItem( itemRepository.findById(itemsId.get(i)).orElse(null)));




    }

    return items;

}
}
