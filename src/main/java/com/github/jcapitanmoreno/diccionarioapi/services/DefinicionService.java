package com.github.jcapitanmoreno.diccionarioapi.services;

import com.github.jcapitanmoreno.diccionarioapi.exceptions.RecordNotFoundException;
import com.github.jcapitanmoreno.diccionarioapi.models.Definicion;
import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import com.github.jcapitanmoreno.diccionarioapi.repositories.DefinicionRepository;
import com.github.jcapitanmoreno.diccionarioapi.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefinicionService {

    @Autowired
    private DefinicionRepository definicionRepository;

    @Autowired
    private PalabraRepository palabraRepository;


    public List<Definicion> getDefinicionesByPalabraId(Long palabraId) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(palabraId);
        if (palabra.isPresent()) {
            return palabra.get().getDefiniciones();
        } else {
            throw new RecordNotFoundException("No existe Palabra para el id: ", palabraId);
        }
    }

    public Definicion addDefinicionToPalabra(Long palabraId, Definicion definicion) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(palabraId);
        if (palabra.isPresent()) {
            definicion.setPalabra(palabra.get());
            return definicionRepository.save(definicion);
        } else {
            throw new RecordNotFoundException("No existe Palabra para el id: ", palabraId);
        }
    }

    public void deleteDefinicion(Long definicionId) throws RecordNotFoundException {
        Optional<Definicion> definicion = definicionRepository.findById(definicionId);
        if (definicion.isPresent()) {
            definicionRepository.deleteById(definicionId);
        } else {
            throw new RecordNotFoundException("No existe Definicion para el id: ", definicionId);
        }
    }
}
