package com.github.jcapitanmoreno.diccionarioapi.controllers;


import com.github.jcapitanmoreno.diccionarioapi.exceptions.RecordNotFoundException;
import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import com.github.jcapitanmoreno.diccionarioapi.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabra")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> palabras = palabraService.getAllPalabras();
        return new ResponseEntity<List<Palabra>>(palabras, new  HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Palabra> updatePalabra(@RequestBody Palabra palabra) throws RecordNotFoundException {
        Palabra updatedPalabra = palabraService.updatePalabra(palabra);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPalabra);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabraById(@PathVariable Long id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }

}
