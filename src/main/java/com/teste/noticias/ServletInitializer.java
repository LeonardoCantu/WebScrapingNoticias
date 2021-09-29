/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teste.noticias;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author Léo
 */
public class ServletInitializer extends SpringBootServletInitializer{
    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(NoticiasApplication.class);
    }
}
