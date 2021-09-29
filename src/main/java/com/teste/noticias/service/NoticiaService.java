package com.teste.noticias.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.Utils;
import com.teste.noticias.model.Elemento;
import com.teste.noticias.model.Noticia;
import com.teste.noticias.model.ElementoTexto;
import com.teste.noticias.model.Filtro;
import com.teste.noticias.repository.NoticiaRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Service
public class NoticiaService {

    private String url = new String();

    private Noticia noticia;

    private Elemento elementos;

    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia cadastrarNoticia(String url) {
        try {
            this.url = url;
            elementos = new Elemento();
            realizaCapturaDosElementos();
            converteElementosEmTexto();
            converteTextoEmObjeto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noticia;
    }

    public List<Noticia> listarNoticias() {
        try {
            return noticiaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Noticia buscarNoticia(Long idNoticia) {
        try {
            this.noticia = noticiaRepository.findById(idNoticia).get();
            montarTextoDaNoticia();
            return this.noticia;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void montarTextoDaNoticia() throws Exception {
        Map<String, String> estruturaDoTexto = new HashMap();
        List<String> texto = new ArrayList<>();
        for (ElementoTexto elemento : noticia.getElementosTexto()) {
            estruturaDoTexto.put(elemento.getElemento(), elemento.getConteudo());
            texto.add(elemento.getConteudo());
        }
        noticia.setMapTextoCompleto(estruturaDoTexto);
        noticia.setTextoCompleto(texto);
        
        
    }

    public List<Noticia> pesquisarNoticia(Filtro filtro) {
        List<Noticia> noticias = new ArrayList<>();
        switch (filtro.getTipo()) {
            case "autor":
                noticias = noticiaRepository.findByAutor(filtro.getConteudo());
                break;
            case "titulo":
                noticias = noticiaRepository.findByTitulo(filtro.getConteudo());
                break;
            case "subtitulo":
                noticias = noticiaRepository.findBySubtitulo(filtro.getConteudo());
                break;
        }
        return noticias;
    }

    private void realizaCapturaDosElementos() throws Exception {

        final Document doc = Jsoup.connect(url).get();

        // Define elementos do cabeçalho da noticia
        elementos.setElementosCabecalho("titulo", doc.getElementsByClass("page-title-1").first());
        elementos.setElementosCabecalho("subtitulo", doc.getElementsByClass("article-lead").first());
        elementos.setElementosCabecalho("autor",
                doc.getElementsByClass("author-name").first().getElementsByTag("a").first());
        elementos.setElementosCabecalho("dataPublicacao",
                doc.getElementsByClass("article-date").first().getElementsByTag("time").first());

        // Define elementos do texto da noticia
        Integer ordemDoElemento = 1;
        for (Element elemento : doc.getElementsByClass("col-md-9 col-lg-8 col-xl-6  m-sm-auto m-lg-0 article-content")
                .get(0).getAllElements()) {

            if (elemento.tagName().equals("h2")) {
                elementos.setElementosTexto(ordemDoElemento, elemento);
                ordemDoElemento++;
                continue;
            }
            if (elemento.tagName().equals("p")) {
                elementos.setElementosTexto(ordemDoElemento, elemento);
                ordemDoElemento++;
            }
        }
    }

    private void converteElementosEmTexto() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        for (String key : elementos.getElementosCabecalho().keySet()) {
            if (!elementos.getElementosCabecalho().get(key).text().isEmpty()) {
                String texto = mapper.writeValueAsString(elementos.getElementosCabecalho().get(key).text());
                if (!texto.isEmpty()) {
                    elementos.setTextoElementosCabecalho(key, texto.substring(1, texto.length() - 1));
                }

            }

        }

        for (Integer key : elementos.getElementosTexto().keySet()) {
            if (!elementos.getElementosTexto().get(key).text().isEmpty()) {
                String texto = mapper.writeValueAsString(elementos.getElementosTexto().get(key).text());
                if (!texto.isEmpty()) {
                    elementos.setTextoElementosTexto(key, texto.substring(1, texto.length() - 1));
                }
            }
        }
    }

    private void converteTextoEmObjeto() throws Exception {

        noticia = new Noticia();
        noticia.setUrl(url);
        noticia.setTitulo(elementos.getTextoElementosCabecalho().get("titulo"));
        noticia.setAutor(elementos.getTextoElementosCabecalho().get("autor"));
        noticia.setDataPublicacao(elementos.getTextoElementosCabecalho().get("dataPublicacao"));
        noticia.setSubtituloCabecalho(elementos.getTextoElementosCabecalho().get("subtitulo"));

        final List<ElementoTexto> listaElementosTexto = new ArrayList<>();
        for (Integer key : elementos.getElementosTexto().keySet()) {
            Element elemento = elementos.getElementosTexto().get(key);
            if (elemento.tagName().equals("h2")) {
                ElementoTexto elementoTexto = new ElementoTexto();
                elementoTexto.setElemento("h2");
                elementoTexto.setNoticia(noticia);
                elementoTexto.setOrdem(key);
                elementoTexto.setConteudo(elementos.getTextoElementosTexto().get(key));
                listaElementosTexto.add(elementoTexto);
            }
            if (elemento.tagName().equals("p") && elemento.getElementsByTag("strong").isEmpty()) {
                ElementoTexto elementosTexto = new ElementoTexto();
                elementosTexto.setElemento("p");
                elementosTexto.setNoticia(noticia);
                elementosTexto.setOrdem(key);
                elementosTexto.setConteudo(elementos.getTextoElementosTexto().get(key));
                listaElementosTexto.add(elementosTexto);
            }
        }
        noticia.setElementosTexto(listaElementosTexto);

        noticiaRepository.save(noticia);
    }

}
