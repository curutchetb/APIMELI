package com.apiMeli.apiMeli.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Clase para acceder a las variables personalizadas
 * de application properties
 * @author Barbara
 *
 */
@Component
public class AppProperties {

    @Autowired
    private Environment env;

    /**
     * Método para obtener la propiedad de token secret desde app properties
     * @return devuelve variable tokenSecret
     */
    public String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }
}
