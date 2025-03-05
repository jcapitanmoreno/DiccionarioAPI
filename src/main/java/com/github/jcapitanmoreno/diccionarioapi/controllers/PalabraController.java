package com.github.jcapitanmoreno.diccionarioapi.controllers;

import com.github.jcapitanmoreno.diccionarioapi.exceptions.RecordNotFoundException;
import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import com.github.jcapitanmoreno.diccionarioapi.services.PalabraService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/palabra")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @CrossOrigin
    @Operation(
            summary = "Obtener todas las palabras",
            description = "Este endpoint permite obtener todas las palabras sin sus definiciones.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabras obtenidas",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "[{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\"}]"))
                    )
            }
    )
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
    @Operation(
            summary = "Obtener todas las palabras con definiciones",
            description = "Este endpoint permite obtener todas las palabras con sus definiciones.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabras obtenidas",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "[{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\",\"definiciones\":[{\"id\":1,\"definicion\":\"Lenguaje de programación orientado a objetos\"}]}]"))
                    )
            }
    )
    @GetMapping("/definiciones")
    public ResponseEntity<List<Palabra>> getAllPalabrasWithDefiniciones() {
        List<Palabra> palabras = palabraService.getAllPalabras();
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @Operation(
            summary = "Obtener una palabra por ID con definiciones",
            description = "Este endpoint permite obtener una palabra específica por su ID, incluyendo sus definiciones.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabra obtenida",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\",\"definiciones\":[{\"id\":1,\"definicion\":\"Lenguaje de programación orientado a objetos\"}]}"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Palabra no encontrada", content = @Content)
            }
    )
    @GetMapping("/{id}/definiciones")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        return new ResponseEntity<>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @Operation(
            summary = "Obtener una palabra por ID sin definiciones",
            description = "Este endpoint permite obtener una palabra específica por su ID, excluyendo sus definiciones.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabra obtenida",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\"}"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Palabra no encontrada", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraPorIdSinDefiniciones(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        if (palabra == null) {
            throw new RecordNotFoundException("No se ha encontrado la palabra", id);
        }
        palabra.setDefiniciones(null);
        return new ResponseEntity<>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @Operation(
            summary = "Crear una nueva palabra",
            description = "Este endpoint permite crear una nueva palabra.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Palabra creada",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\"}"))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @Operation(
            summary = "Actualizar una palabra existente",
            description = "Este endpoint permite actualizar una palabra existente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabra actualizada",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\"}"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Palabra no encontrada", content = @Content)
            }
    )
    @PutMapping
    public ResponseEntity<Palabra> updatePalabra(@RequestBody Palabra palabra) throws RecordNotFoundException {
        Palabra updatedPalabra = palabraService.updatePalabra(palabra);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPalabra);
    }

    @CrossOrigin
    @Operation(
            summary = "Eliminar una palabra por ID",
            description = "Este endpoint permite eliminar una palabra específica por su ID.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Palabra eliminada", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Palabra no encontrada", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabraById(@PathVariable Long id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }

    @CrossOrigin
    @Operation(
            summary = "Crear una nueva palabra con definiciones",
            description = "Este endpoint permite crear una nueva palabra junto con sus definiciones.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Palabra creada",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\",\"definiciones\":[{\"id\":1,\"definicion\":\"Lenguaje de programación orientado a objetos\"}]}"))
                    )
            }
    )
    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> createPalabraWithDefiniciones(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabraWithDefiniciones(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @Operation(
            summary = "Obtener palabras por categoría",
            description = "Este endpoint permite obtener todas las palabras que pertenecen a una categoría gramatical específica.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabras obtenidas",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "[{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\"}]"))
                    )
            }
    )
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> palabras = palabraService.getPalabrasByCategoria(categoria);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @Operation(
            summary = "Obtener palabras por inicial",
            description = "Este endpoint permite obtener todas las palabras que comienzan con una letra específica.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Palabras obtenidas",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "[{\"id\":1,\"termino\":\"java\",\"categoriaGramatical\":\"sustantivo\"}]"))
                    )
            }
    )
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getPalabrasByInicial(@PathVariable String letra) {
        List<Palabra> palabras = palabraService.getPalabrasByInicial(letra);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }
}