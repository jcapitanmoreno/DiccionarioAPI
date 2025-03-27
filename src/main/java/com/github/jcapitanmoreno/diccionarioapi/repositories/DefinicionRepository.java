package com.github.jcapitanmoreno.diccionarioapi.repositories;

import com.github.jcapitanmoreno.diccionarioapi.models.Definicion;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@ComponentScan
@Repository
public interface DefinicionRepository extends JpaRepository<Definicion, Long> {


}
