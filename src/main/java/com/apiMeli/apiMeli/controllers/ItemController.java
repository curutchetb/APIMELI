package com.apiMeli.apiMeli.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apiMeli.apiMeli.models.responses.OperationStatusModel;
import com.apiMeli.apiMeli.models.responses.FavoritosRest;
import com.apiMeli.apiMeli.models.responses.ItemRest;
import com.apiMeli.apiMeli.services.ItemServiceInterface;
import com.apiMeli.apiMeli.services.UserServiceInterface;
import com.apiMeli.apiMeli.shared.dto.FavoritosDto;
import com.apiMeli.apiMeli.shared.dto.ItemDto;
import com.apiMeli.apiMeli.shared.dto.UserDto;

/**
 * Clase encargada de lo relacionado a los items
 * @author Barbara
 *
 */
@RestController
public class ItemController {

    @Autowired
    ItemServiceInterface itemService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserServiceInterface userService;

    /**
     * Método para obtener un item especifico
     * @param id del item
     * @return datos del item especifico
     */
    @GetMapping(path = "/items/{id}")
    public ItemRest getItem(@PathVariable String id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto user = userService.getUser(authentication.getPrincipal().toString());

        ItemDto itemDto = itemService.getItem(id, user.getId());

        ItemRest itemRest = mapper.map(itemDto, ItemRest.class);

        return itemRest;
    }

    /**
     * Método para eliminar un item especifico
     * @param id del item especifico
     * @return status ante la eliminacion
     */
    @DeleteMapping(path = "/items/{id}")
    public OperationStatusModel deleteItem(@PathVariable String id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto user = userService.getUser(authentication.getPrincipal().toString());

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setName("DELETE");
        itemService.deleteItem(id, user.getId());
        operationStatusModel.setResult("SUCCESS");

        return operationStatusModel;
    }

    /**
     * Método para obtener la lista de los items mas favoriteados al utilizar el coupon
     * @return lista de los items mas favoriteados al utilizar el coupon y la cantidad de veces marcados como favorito
     */
    @GetMapping(path = "coupon/favorites")
    public String getItemsFavoritos() {

        List<FavoritosDto> itemFavoritos = itemService.getItemsFavoritos();

        List<FavoritosRest> favRests = new ArrayList<>();

        for (FavoritosDto itemsFavDtos : itemFavoritos) {
            FavoritosRest favRest = mapper.map(itemsFavDtos, FavoritosRest.class);
            favRests.add(favRest);
        }

        return favRests.toString();
    }
}
