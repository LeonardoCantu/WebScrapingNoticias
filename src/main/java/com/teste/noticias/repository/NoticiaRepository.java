package com.teste.noticias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.noticias.model.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    @Query(value = "SELECT * FROM TAB_NOTICIA  WHERE TITULO ILIKE %:titulo% ",nativeQuery = true)
    List<Noticia> findByTitulo( String titulo);
    
    @Query(value ="SELECT * FROM TAB_NOTICIA  WHERE SUBTITULO_CABECALHO ILIKE %:subtitulo%",nativeQuery = true)
    List<Noticia> findBySubtitulo(String subtitulo);
    
    @Query(value ="SELECT * FROM TAB_NOTICIA WHERE AUTOR ILIKE %:autor%",nativeQuery = true)
    List<Noticia> findByAutor(String autor);
 
}
