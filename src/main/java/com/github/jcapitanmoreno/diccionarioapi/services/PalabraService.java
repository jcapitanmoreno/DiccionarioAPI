package com.github.jcapitanmoreno.diccionarioapi.services;

import com.github.jcapitanmoreno.diccionarioapi.exceptions.RecordNotFoundException;
import com.github.jcapitanmoreno.diccionarioapi.models.Definicion;
import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import com.github.jcapitanmoreno.diccionarioapi.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PalabraService {

    @Autowired
    private PalabraRepository palabraRepository;

    /**
     * Obtiene todas las palabras.
     * @return una lista de todas las palabras.
     */
    public List<Palabra> getAllPalabras() {
        List<Palabra> palabras = palabraRepository.findAll();
        if (!palabras.isEmpty()) {
            return palabras;
        } else {
            return new ArrayList<Palabra>();
        }
    }

    /**
     * Obtiene una palabra por su ID.
     * @param id el ID de la palabra.
     * @return la palabra correspondiente al ID.
     * @throws RecordNotFoundException si no se encuentra la palabra.
     */
    public Palabra getPalabraById(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(id);
        if (palabra.isPresent()) {
            return palabra.get();
        } else {
            throw new RecordNotFoundException("No existe Palabra para el id ", id);
        }
    }

    /**
     * Crea una nueva palabra.
     * @param palabra la palabra a crear.
     * @return la palabra creada.
     */
    public Palabra createPalabra(Palabra palabra) {
        palabra = palabraRepository.save(palabra);
        return palabra;
    }

    /**
     * Actualiza una palabra existente.
     * @param palabra la palabra a actualizar.
     * @return la palabra actualizada.
     * @throws RecordNotFoundException si no se encuentra la palabra.
     */
    public Palabra updatePalabra(Palabra palabra) throws RecordNotFoundException {
        if (palabra.getId() != null) {
            Optional<Palabra> palabraOptional = palabraRepository.findById(palabra.getId());
            if (palabraOptional.isPresent()) {
                Palabra newPalabra = palabraOptional.get();
                newPalabra.setTermino(palabra.getTermino());
                newPalabra.setCategoriaGramatical(palabra.getCategoriaGramatical());
                newPalabra.setDefiniciones(palabra.getDefiniciones());
                newPalabra = palabraRepository.save(newPalabra);
                return newPalabra;
            } else {
                throw new RecordNotFoundException("No existe Palabra para el id: ", palabra.getId());
            }
        } else {
            throw new RecordNotFoundException("No hay id en la palabra a actualizar ", 0L);
        }
    }

    /**
     * Elimina una palabra por su ID.
     * @param id el ID de la palabra a eliminar.
     * @throws RecordNotFoundException si no se encuentra la palabra.
     */
    public void deletePalabra(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (palabraOptional.isPresent()) {
            palabraRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No existe Palabra para el id: ", id);
        }
    }

    /**
     * Crea una nueva palabra junto con sus definiciones.
     * @param palabra la palabra a crear.
     * @return la palabra creada.
     */
    public Palabra createPalabraWithDefiniciones(Palabra palabra) {
        for (Definicion definicion : palabra.getDefiniciones()) {
            definicion.setPalabra(palabra);
        }
        return palabraRepository.save(palabra);
    }

    /**
     * Obtiene una lista de palabras por categoría gramatical.
     * @param categoria la categoría gramatical.
     * @return una lista de palabras que pertenecen a la categoría gramatical.
     */
    public List<Palabra> getPalabrasByCategoria(String categoria) {
        return palabraRepository.findByCategoriaGramatical(categoria);
    }

    /**
     * Obtiene una lista de palabras por inicial.
     * @param letra la letra inicial.
     * @return una lista de palabras que comienzan con la letra especificada.
     */
    public List<Palabra> getPalabrasByInicial(String letra) {
        return palabraRepository.findByTerminoStartingWith(letra);
    }
}