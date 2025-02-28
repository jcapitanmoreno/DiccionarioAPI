package com.github.jcapitanmoreno.diccionarioapi.controllers;

import com.github.jcapitanmoreno.diccionarioapi.exceptions.RecordNotFoundException;
import com.github.jcapitanmoreno.diccionarioapi.models.Definicion;
import com.github.jcapitanmoreno.diccionarioapi.services.DefinicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/definicion")
public class DefinicionController {

    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @GetMapping("/palabra/{palabraId}")
    public ResponseEntity<List<Definicion>> getDefinicionesByPalabraId(@PathVariable Long palabraId) throws RecordNotFoundException {
        List<Definicion> definiciones = definicionService.getDefinicionesByPalabraId(palabraId);
        return new ResponseEntity<>(definiciones, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/palabra/{palabraId}")
    public ResponseEntity<Definicion> addDefinicionToPalabra(@PathVariable Long palabraId, @RequestBody Definicion definicion) throws RecordNotFoundException {
        Definicion createdDefinicion = definicionService.addDefinicionToPalabra(palabraId, definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinicion);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicionById(@PathVariable Long id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }

}
