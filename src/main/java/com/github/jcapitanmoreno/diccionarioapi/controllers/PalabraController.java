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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/palabra")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> palabras = palabraService.getAllPalabras();
        List<Palabra> palabrasSinDefiniciones = palabras.stream()
                .map(palabra -> {
                    palabra.setDefiniciones(null);
                    return palabra;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(palabrasSinDefiniciones, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/definiciones")
    public ResponseEntity<List<Palabra>> getAllPalabrasWithDefiniciones() {
        List<Palabra> palabras = palabraService.getAllPalabras();
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
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

    @CrossOrigin
    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> createPalabraWithDefiniciones(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabraWithDefiniciones(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> palabras = palabraService.getPalabrasByCategoria(categoria);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getPalabrasByInicial(@PathVariable String letra) {
        List<Palabra> palabras = palabraService.getPalabrasByInicial(letra);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

}
