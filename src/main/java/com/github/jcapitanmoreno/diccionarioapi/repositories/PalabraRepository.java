package com.github.jcapitanmoreno.diccionarioapi.repositories;

import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Long> {

    /**
     * Encuentra una lista de palabras por su categoría gramatical.
     * @param categoria la categoría gramatical de las palabras.
     * @return una lista de palabras que pertenecen a la categoría gramatical especificada.
     */
    List<Palabra> findByCategoriaGramatical(String categoria);

    /**
     * Encuentra una lista de palabras que comienzan con una letra específica.
     * @param letra la letra inicial de las palabras.
     * @return una lista de palabras que comienzan con la letra especificada.
     */
    List<Palabra> findByTerminoStartingWith(String letra);
}