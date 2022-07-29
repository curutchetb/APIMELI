package com.apiMeli.apiMeli.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apiMeli.apiMeli.services.UserServiceInterface;

/**
 * Clase encargada de la configuracion la seguridad de la aplicacion
 * @author Barbara
 *
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserServiceInterface userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**Contructor.
     * @param userService servicio del usuario a utilizar
     * @param bCryptPasswordEncoder algoritmo utilizado para encriptar contraseña
     */
    public WebSecurity(UserServiceInterface userService, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //configuracion de la seguridad de la aplicacion segun sus endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/items/last").permitAll()
                .antMatchers(HttpMethod.GET, "/items/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/coupon/favorites").permitAll()
                .anyRequest()
                .authenticated()
                .and().addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        /*
         * al establecerse conexion entre el cliente y el servidor
         * no se crea varible de sesion porque se usa session web token y header de
         * authorization para enviar token en cada peticion
         */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);

    }

    /**
     * Personalizar nombre url login
     * @return retorna url de login personalizado
     * @throws Exception Excepcion en caso de que el url no exista
     */
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());

        filter.setFilterProcessesUrl("/users/login");

        return filter;
    }
}
