package com.apiMeli.apiMeli;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Clase para acceder al contexto de la app y desde ahi
 * poder acceder a los beans que se han creado y poder autowired
 * 
 * @author Barbara
 *
 */

public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    /**
     * ApplicationContext a ser usado. 
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    /**
     * 
     * @param beanName nombre del bean
     * @return  retorna el contexto del bean recibido por parametro
     */
    public static Object getBean(String beanName){
        return CONTEXT.getBean(beanName);
    }
}
