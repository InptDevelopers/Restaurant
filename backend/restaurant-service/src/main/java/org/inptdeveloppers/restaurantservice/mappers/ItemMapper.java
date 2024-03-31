package org.inptdeveloppers.restaurantservice.mappers;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.DTOS.ItemDTO;
import org.inptdeveloppers.restaurantservice.DTOS.MenuDTO;
import org.inptdeveloppers.restaurantservice.entities.Item;
import org.inptdeveloppers.restaurantservice.repositories.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemMapper {
    private final MenuRepository menuRepository;
    public ItemDTO fromItem(Item item){
        ItemDTO itemDTO=new ItemDTO();
        BeanUtils.copyProperties(item,itemDTO);

        return itemDTO;

    }
    public Item fromItemDTO(ItemDTO itemDTO){
        Item item=new Item();
        BeanUtils.copyProperties(itemDTO,item);

        return item;

    }
}
