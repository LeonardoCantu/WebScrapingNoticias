package com.teste.noticias.controller;

import com.teste.noticias.model.Filtro;
import com.teste.noticias.model.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.teste.noticias.service.NoticiaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;
 
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView noticia() {
        ModelAndView modelAndView = new ModelAndView();
        Noticia noticia = new Noticia();
        modelAndView.setViewName("noticia");
        modelAndView.addObject("noticia", noticia);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView cadastrarNoticia(Noticia noticia) {
        noticia = noticiaService.cadastrarNoticia(noticia.getUrl());
        if(noticia == null){
            return noticia();
        }
        return visualizarNoticia(noticia.getId());
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public ModelAndView listarNoticias(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticia_listar");
        modelAndView.addObject("noticias", noticiaService.listarNoticias());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visualizar/{idNoticia}")
    public ModelAndView visualizarNoticia(@PathVariable Long idNoticia){
        Noticia noticia = new Noticia();
        noticia = noticiaService.buscarNoticia(idNoticia);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticia_visualizar");
        modelAndView.addObject("noticia", noticia);
        return modelAndView;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
    public ModelAndView pesquisarNoticia(){
        Filtro filtro = new Filtro();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticia_buscar");
        modelAndView.addObject("filtro", filtro );
        return modelAndView;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/pesquisar")
    public ModelAndView pesquisarNoticia(Filtro filtro){      
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticia_listar");
        modelAndView.addObject("noticias", noticiaService.pesquisarNoticia(filtro));
        return modelAndView;
    }
}
