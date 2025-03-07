package com.github.jcapitanmoreno.diccionarioapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Palabra")
public class Palabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String termino;

    private String categoriaGramatical;

    @JsonManagedReference
    @OneToMany(mappedBy = "palabra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Definicion> definiciones;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getCategoriaGramatical() {
        return categoriaGramatical;
    }

    public void setCategoriaGramatical(String categoriaGramatical) {
        this.categoriaGramatical = categoriaGramatical;
    }

    public List<Definicion> getDefiniciones() {
        return definiciones;
    }

    public void setDefiniciones(List<Definicion> definiciones) {
        this.definiciones = definiciones;
    }
}