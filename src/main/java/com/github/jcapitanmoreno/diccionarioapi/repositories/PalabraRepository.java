package com.github.jcapitanmoreno.diccionarioapi.repositories;

import com.github.jcapitanmoreno.diccionarioapi.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabraRepository extends JpaRepository<Palabra, Long> {

}
