package com.apiMeli.apiMeli.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiMeli.apiMeli.models.requests.CouponRequestModel;
import com.apiMeli.apiMeli.models.requests.UserDetailRequestModel;
import com.apiMeli.apiMeli.models.responses.CouponRest;
import com.apiMeli.apiMeli.models.responses.ItemRest;
import com.apiMeli.apiMeli.models.responses.UserRest;
import com.apiMeli.apiMeli.services.UserServiceInterface;
import com.apiMeli.apiMeli.shared.dto.CouponDto;
import com.apiMeli.apiMeli.shared.dto.ItemDto;
import com.apiMeli.apiMeli.shared.dto.UserDto;

/**
 * Clase encargada de lo relacionado con el inicio de sesion
 * @author Barbara
 *
 */

@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper;

    /**
     * Metodo GET para obtener el usuario
     * @return retorna los datos del user 
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public UserRest getUser() {
    	
    	//Se accede  al contexto de seguridad de la app y obtener la autenticacion del user
    	//y acceder al el email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        UserDto userDto = userService.getUser(email);

        // metodo map mapea copia un bean a otro en profundidad
        UserRest userToReturn = mapper.map(userDto, UserRest.class);

        return userToReturn;

    }

    /**
     * Metodo POST para enviar datos y crear user
     * @param userDetails se envía por el request body los datos para
     * crear user
     * @return retorna datos de user creado 
     */
    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserDetailRequestModel userDetails) {
        /*
         * estos pasos se hacen por seguridad y para tener el codigo organizado y saber
         * para que se
         * utiliza cada clase
         */

        UserRest userToReturn = new UserRest(); // se crea objeto que se va a retornar

        UserDto userDto = new UserDto(); // objeto que se enviara a la logica de nuestra app

        BeanUtils.copyProperties(userDetails, userDto); // copiamos propiedades del objeto que recibimos al DTO

        UserDto createUser = userService.createUser(userDto); // service conecta controlador con logica del sistema

        BeanUtils.copyProperties(createUser, userToReturn);

        return userToReturn;
    }

    /**
     * Metodo GET para obtener lista de items del user autenticado
     * @return retorna lista de items del user
     */
    @GetMapping(path = "/items")
    public List<ItemRest> getItems() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        List<ItemDto> items = userService.getUserItems(email);
        List<ItemRest> itemRests = new ArrayList<>();

        // convertir lista de ItemDTO en ItemRest
        for (ItemDto item : items) {
            ItemRest itemRest = mapper.map(item, ItemRest.class);

            itemRests.add(itemRest);
        }

        return itemRests;
    }

    /**
     * 
     * @param couponRequestModel se envía por el request body la lista
     * de items favoritos con el monto del coupon
     * @return retorna la lista de items posibles de compra con el coupon
     * y el monto total
     * 
     */
    @PostMapping(path = "/coupon")
    public CouponRest getItemCoupon(@RequestBody @Valid CouponRequestModel couponRequestModel)  {

        CouponDto couponDto = new CouponDto(); // objeto que se enviara a la logica de nuestra app

        BeanUtils.copyProperties(couponRequestModel, couponDto); // copiamos propiedades del objeto que recibimos al DTO

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        CouponDto items = userService.getItemCoupon(email, couponDto);

        // convertir ItemDTO en ItemRest
        CouponRest itemRest = mapper.map(items, CouponRest.class);

        return itemRest;
    }

}
