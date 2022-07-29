package com.apiMeli.apiMeli.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.apiMeli.apiMeli.SpringApplicationContext;
import com.apiMeli.apiMeli.models.requests.UserLoginRequestModel;
import com.apiMeli.apiMeli.services.UserServiceInterface;
import com.apiMeli.apiMeli.shared.dto.UserDto;

/**
 * Clase que sirve para el inicio de sesion
 * desciende de la clase usernamepasswordauthenticationfilter de SECURITY 
 * @author Barbara
 *
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        /**
         * se pasa lo que viene en json hacia la clase en java requestModel - mail y password 
         */
        try {
            UserLoginRequestModel userModel = new ObjectMapper().readValue(request.getInputStream(),
                    UserLoginRequestModel.class);
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userModel.getEmail(),
                            userModel.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
        String username = ((User) authentication.getPrincipal()).getUsername();

        /**
         * se crea el token para autenticar
         */
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();


        /**
         * Se accede al contexto de la app y asi obtener el bean clase userService
         * para luego poder acceder a los metodos de esa clase (es como un autowired)
         */
        UserServiceInterface userService = (UserServiceInterface) SpringApplicationContext.getBean("userService");
        UserDto userDto = userService.getUser(username);
        
        /**
         * añadir header con id publico
         * añadir header con el token 
         */
        response.addHeader("Access-Control-Expose-Headers", "Authorization, UserId");
        response.addHeader("userId", userDto.getUserId());

        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

    }
}
