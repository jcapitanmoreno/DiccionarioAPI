package com.github.jcapitanmoreno.diccionarioapi.repositories;

import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalabraRepository extends JpaRepository<Palabra, Long> {
}