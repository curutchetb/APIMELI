package com.apiMeli.apiMeli.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiMeli.apiMeli.repositories.ItemRepository;
import com.apiMeli.apiMeli.repositories.UserRepository;
import com.apiMeli.apiMeli.shared.dto.CouponDto;
import com.apiMeli.apiMeli.shared.dto.ItemDto;
import com.apiMeli.apiMeli.shared.dto.UserDto;
import com.apiMeli.apiMeli.entities.ItemEntity;
import com.apiMeli.apiMeli.entities.UserEntity;
import com.apiMeli.apiMeli.exceptions.EmailExistsException;

/**
 * Clase User Service donde se implementa la logica de los metodos de la interfaz UserServiceInterface
 * @author Barbara
 *
 */
@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ConnectionService connection;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto user) {

        if(userRepository.findByEmail(user.getEmail()) != null) 
        throw new EmailExistsException("El correo electronico ya existe");
        
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        //se genera Id aleatorio
        UUID userId = UUID.randomUUID();
        userEntity.setUserId(userId.toString());

        //encriptar contraseña
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(userEntity, userToReturn);

        return userToReturn;


    }

    @Override
    public List<ItemDto> getUserItems(String email) {
        
        UserEntity userEntity = userRepository.findByEmail(email);
        
        List<ItemEntity> items = itemRepository.getByUserId(userEntity.getId());

        List<ItemDto> itemDtos = new ArrayList<>();

        for (ItemEntity item : items) {
            ItemDto itemDto = mapper.map(item, ItemDto.class);
            itemDtos.add(itemDto);
        }
        
        return itemDtos;
    }

    @Override
    public CouponDto getItemCoupon(String email, @Valid CouponDto couponModel) {

        // obtener usuario con email especifico
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new RuntimeException("Debe loguearse primero");
        }

        // se arma map para items con precios
        Map<String, Double> itemList = new HashMap<>();

        // se eliminan duplicados
        couponModel.setItemId(couponModel.getItemId().stream().distinct().collect(Collectors.toList()));

        for (String item : couponModel.getItemId()) {

            // se agrega a una lista para despues comparar
            itemList.putAll(connection.getItem(item));

        }

        for (Map.Entry<String, Double> pair : itemList.entrySet()) {
            // valida que no haya duplicados en el repo
            if (itemRepository.getByUserId(userEntity.getId()).stream()
                    .filter(p -> p.getItemId().equalsIgnoreCase(pair.getKey())).findAny().isEmpty()) {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setUser(userEntity);
                itemEntity.setItemId(pair.getKey());
                itemEntity.setPrice(pair.getValue());
                itemRepository.save(itemEntity);
            }

        }

        List<Map.Entry<String, Double>> list = new ArrayList<>(itemList.entrySet());

        // ordeno de menor a mayor
        list.sort(Entry.comparingByValue());

        int suma = 0;
        itemList.clear();

        List<String> itemsPorCoupon = new ArrayList<>();

        // con la itemList vacia solo añado los items posibles de compra gratis
        for (Map.Entry<String, Double> pair : list) {
            if (suma + pair.getValue() < couponModel.getAmount()) {
                suma += pair.getValue();
                itemsPorCoupon.add(pair.getKey());
            } else
                break;
        }

        // objeto a retornar
        CouponDto couponDtos = new CouponDto();

        couponDtos.setItemId(itemsPorCoupon);
        couponDtos.setAmount(suma);

        return couponDtos;
    }

}