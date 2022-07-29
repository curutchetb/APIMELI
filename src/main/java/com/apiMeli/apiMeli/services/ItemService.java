package com.apiMeli.apiMeli.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiMeli.apiMeli.repositories.ItemRepository;
import com.apiMeli.apiMeli.repositories.UserRepository;
import com.apiMeli.apiMeli.shared.dto.FavoritosDto;
import com.apiMeli.apiMeli.shared.dto.ItemDto;
import com.apiMeli.apiMeli.entities.ItemEntity;
import com.apiMeli.apiMeli.exceptions.ItemExistanceException;

/**
 * Clase Item Service donde se implementa la logica de los metodos de la interfaz ItemServiceInterface
 * @author Barbara
 *
 */
@Service
public class ItemService implements ItemServiceInterface{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public ItemDto getItem(String itemId, long userId) {
        
        Optional<ItemEntity> item = itemRepository.getByUserId(userId).stream().filter(p -> p.getItemId().equalsIgnoreCase(itemId)).findAny();

        if(item.isEmpty()){
            throw new ItemExistanceException("No tienes este item entre sus favoritos");
        }
        ItemEntity itemEntity = item.get();

        ItemDto itemDto = mapper.map(itemEntity, ItemDto.class);

        return itemDto;
    }


    @Override
    public void deleteItem(String itemId, long userId) {

            Optional<ItemEntity> item = itemRepository.getByUserId(userId).stream().filter(p -> p.getItemId().equalsIgnoreCase(itemId)).findAny();

            if(item.isEmpty()){
                throw new ItemExistanceException("No existe este item entre sus favoritos");
            }
            else{
                ItemEntity itemEntity = item.get();
                itemRepository.delete(itemEntity);
            }  
    }


    @Override
    public List<FavoritosDto> getItemsFavoritos() {
        
        //obtengo todo el repo
        List<ItemEntity> itemFavoritos =itemRepository.findAll();

        //obtengo lista solo de itemsId
        List<String> itemId = itemFavoritos.stream().map(p -> p.getItemId())
        .collect(Collectors.toList());

        List<FavoritosDto> itemsFav = new ArrayList<>();

        //en la lista de DTO paso los items_id con su frecuencia
        for (String item : itemId) {
            itemsFav.add(new FavoritosDto(item,Collections.frequency(itemId, item)));
          }

        //elimino repetidos, ordeno de mayor a menor y obtengo los primeros 5
        itemsFav = itemsFav.stream().collect(Collectors.toMap(FavoritosDto::getItemId, p -> p, (p, q) -> p)).values().stream().sorted(Comparator.comparingInt(FavoritosDto::getnumber).reversed()).limit(5).collect(Collectors.toList());

        return itemsFav;
    }    
}
