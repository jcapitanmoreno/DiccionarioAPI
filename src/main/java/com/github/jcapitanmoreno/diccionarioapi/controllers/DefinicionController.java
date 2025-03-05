package com.github.jcapitanmoreno.diccionarioapi.controllers;

import com.github.jcapitanmoreno.diccionarioapi.exceptions.RecordNotFoundException;
import com.github.jcapitanmoreno.diccionarioapi.models.Definicion;
import com.github.jcapitanmoreno.diccionarioapi.services.DefinicionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
    @Operation(
            summary = "Obtener definiciones por ID de palabra",
            description = "Este endpoint permite obtener todas las definiciones asociadas a una palabra específica.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Definiciones obtenidas",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "[{\"id\":1,\"definicion\":\"Lenguaje de programación orientado a objetos\"}]"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Palabra no encontrada", content = @Content)
            }
    )
    @GetMapping("/palabra/{palabraId}")
    public ResponseEntity<List<Definicion>> getDefinicionesByPalabraId(@PathVariable Long palabraId) throws RecordNotFoundException {
        List<Definicion> definiciones = definicionService.getDefinicionesByPalabraId(palabraId);
        return new ResponseEntity<>(definiciones, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @Operation(
            summary = "Agregar una definición a una palabra",
            description = "Este endpoint permite agregar una nueva definición a una palabra existente. Se asume que la palabra ya existe en la base de datos.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Definición creada",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"id\":1,\"definicion\":\"Nueva definición\"}"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Palabra no encontrada", content = @Content)
            }
    )
    @PostMapping("/palabra/{palabraId}")
    public ResponseEntity<Definicion> addDefinicionToPalabra(@PathVariable Long palabraId, @RequestBody Definicion definicion) throws RecordNotFoundException {
        Definicion createdDefinicion = definicionService.addDefinicionToPalabra(palabraId, definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinicion);
    }

    @CrossOrigin
    @Operation(
            summary = "Eliminar una definición por ID",
            description = "Este endpoint permite eliminar una definición específica por su ID.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Definición eliminada", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Definición no encontrada", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicionById(@PathVariable Long id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }
}