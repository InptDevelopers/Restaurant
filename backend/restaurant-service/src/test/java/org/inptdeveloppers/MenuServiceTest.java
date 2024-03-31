package org.inptdeveloppers;


import org.inptdeveloppers.restaurantservice.DTOS.ItemDTO;
import org.inptdeveloppers.restaurantservice.entities.Item;
import org.inptdeveloppers.restaurantservice.mappers.ItemMapper;
import org.inptdeveloppers.restaurantservice.repositories.ItemRepository;
import org.inptdeveloppers.restaurantservice.repositories.MenuRepository;
import org.inptdeveloppers.restaurantservice.repositories.RestaurantRepository;
import org.inptdeveloppers.restaurantservice.services.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private MenuService menuService;

    @Test
    public void testGetItems() {
        // Arrange
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(1L);
        itemIds.add(2L);
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());

        when(itemRepository.findById(1L)).thenReturn(Optional.of(items.get(0)));
        when(itemRepository.findById(2L)).thenReturn(Optional.of(items.get(1)));
        

        // Act
        List<ItemDTO> result = menuService.getitems(itemIds);

        // Assert
        assertEquals(2, result.size());
        // Add more assertions if needed
    }
}