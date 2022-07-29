package com.apiMeli.apiMeli;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apiMeli.apiMeli.models.responses.CouponRest;
import com.apiMeli.apiMeli.models.responses.UserRest;
import com.apiMeli.apiMeli.security.AppProperties;
import com.apiMeli.apiMeli.shared.dto.CouponDto;
import com.apiMeli.apiMeli.shared.dto.UserDto;

/**
 * Esta es la clase principal main que debe 
 * ejecutarse para comenzar con la aplicacion
 * 
 * @author Barbara
 */

@SpringBootApplication
public class ApiMeliApplication {
	
	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiMeliApplication.class, args);
		System.out.println("Funcionando");
	}
	
	/**
	 * Encriptar Contraseñas mediante algoritmo de Bcrypt.
	 * Se crea bean para luego poder hacer un autowired
	 * @return Devuelve contraseña utilizando BCrypt
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder();
	}

	/**
	 * responsable de instanciar, configurar y ensamblar los beans
	 * @return el contexto del bean utilizado
	 */
	@Bean
	public SpringApplicationContext springApplicationContext () {
		return new SpringApplicationContext();
	}

	/**
	 * Metodo para acceder a las variables de application.properties
	 * @return devuelve variable personalizada
	 * 
	 */
	@Bean(name ="AppProperties")
	public AppProperties appProperties () {
		return new AppProperties();
	}

	/**
	 * Metodo que mapea instancias manteniendo su configuracion
	 * @return devuelve instancia del mapper para luego no tener que crearlas
	 */
	@Bean
	public ModelMapper modelMapper (){

		ModelMapper mapper = new ModelMapper();

		//se saltea el userrest al mapear para no entrar en ciclo infinito de user-item-user-item
		mapper.typeMap(UserDto.class, UserRest.class)
			.addMappings(m -> m.skip(UserRest::setItems));
		
		//mappea de una clase a otra con distintos nombres de variables
		mapper.typeMap(CouponDto.class, CouponRest.class)
			.addMappings(m -> m.map(CouponDto::getAmount, CouponRest::setTotal));
		return mapper;

		}
	


}
