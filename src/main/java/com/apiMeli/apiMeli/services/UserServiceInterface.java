package com.apiMeli.apiMeli.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.apiMeli.apiMeli.shared.dto.CouponDto;
import com.apiMeli.apiMeli.shared.dto.ItemDto;
import com.apiMeli.apiMeli.shared.dto.UserDto;

/**
 * Interfaz de User Service donde se declaran los metodos a utilizarse en la clase ItemService
 * @author Barbara
 *
 */
public interface UserServiceInterface extends UserDetailsService {
    
	/**
	 * Método para crear usuario
	 * @param user datos del user a crear
	 * @return datos del user creado
	 */
    public UserDto createUser(UserDto user);
    
    /**
     * Método para obtener datos del user
     * @param email email del user
     * @return retorna datos del user
     */
    public UserDto getUser(String email);
    
    /**
     * Método para obtener la lista de los items favoritos del user
     * @param email del user
     * @return retorna lista de los items favoritos del user 
     */
    public List<ItemDto> getUserItems(String email);
    
    /**
     * Método para obtener la lista de items posibles de compra con el coupon
     * @param email del user
     * @param couponRequestModel lista de items favoritos y el monto del coupon
     * @return lista de items posibles de compra con el coupon
     */
    public CouponDto getItemCoupon(String email, CouponDto couponRequestModel);
}
