package com.apiMeli.apiMeli.security;

import com.apiMeli.apiMeli.SpringApplicationContext;

/**
 * Clase para JsonWeb security tokens autenticacion
 * @author Barbara
 *
 */
public class SecurityConstants {
	
	/**
	 * tiempo en que el token es valido - 2 dias a milisegundos
	 */
    public static final long EXPIRATION_DATE = 172800000;
    
    /**
     * JsonWeb tokens
     */
    public static final String TOKEN_PREFIX = "Bearer "; 
    
    /**
     * Header por donde se envía web token
     */
    public static final String HEADER_STRING = "Authorization";
    
    /**
     * URL por donde los users se registran
     */
    public static final String SIGN_UP_URL = "/users";

    /**
     * Metodo para acceder a las constantes del application properties
     * @return retorna el token configurado en application properties
     */
    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");

        return appProperties.getTokenSecret();
    }

}
