package com.github.jcapitanmoreno.diccionarioapi.repositories;

import com.github.jcapitanmoreno.diccionarioapi.models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinicionRepository extends JpaRepository<Definicion, Long> {
}
