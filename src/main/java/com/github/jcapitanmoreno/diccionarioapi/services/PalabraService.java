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
    private  PalabraRepository palabraRepository;



    public List<Palabra> getAllPalabras() {

        List<Palabra> palabras = palabraRepository.findAll();

        if (!palabras.isEmpty()) {
            return palabras;
        } else {
            return new  ArrayList<Palabra>();
        }

    }

    public Palabra getPalabraById(Long id) throws RecordNotFoundException {

        Optional<Palabra> palabra = palabraRepository.findById(id);


        if (palabra.isPresent()) {
            return palabra.get();
        } else {
            throw  new RecordNotFoundException("No existe Pelicula para el id ",id);
        }
    }

    public Palabra createPalabra(Palabra palabra) {
        palabra = palabraRepository.save(palabra);
        return palabra;
    }

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
            throw new RecordNotFoundException("No hay id en la palabra a actualizar ", 0l);
        }
    }

    public void deletePalabra(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (palabraOptional.isPresent()) {
            palabraRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No existe Palabra para el id: ", id);
        }
    }
}
