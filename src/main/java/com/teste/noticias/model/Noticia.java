package com.teste.noticias.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TAB_NOTICIA")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "DATA_PUBLICACAO")
    private String dataPublicacao;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "SUBTITULO_CABECALHO")
    private String subtituloCabecalho;

    @OneToMany(mappedBy = "noticia", targetEntity = ElementoTexto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ElementoTexto> ElementosTexto;

    @Transient 
    private  Map<String, String> mapTextoCompleto = new HashMap();;
    
    @Transient
    private List<String> textoCompleto;
    
    public Noticia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtituloCabecalho() {
        return subtituloCabecalho;
    }

    public void setSubtituloCabecalho(String subtituloCabecalho) {
        this.subtituloCabecalho = subtituloCabecalho;
    }

    public List<ElementoTexto> getElementosTexto() {
        return ElementosTexto;
    }

    public void setElementosTexto(List<ElementoTexto> ElementosTexto) {
        this.ElementosTexto = ElementosTexto;
    }

    public Map<String, String> getMapTextoCompleto() {
        return mapTextoCompleto;
    }

    public void setMapTextoCompleto(Map<String, String> mapTextoCompleto) {
        this.mapTextoCompleto = mapTextoCompleto;
    }

    public List<String> getTextoCompleto() {
        return textoCompleto;
    }

    public void setTextoCompleto(List<String> textoCompleto) {
        this.textoCompleto = textoCompleto;
    }

}
