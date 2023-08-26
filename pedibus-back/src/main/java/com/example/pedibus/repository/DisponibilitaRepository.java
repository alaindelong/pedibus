package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Disponibilita;

@Repository
public interface DisponibilitaRepository 
extends JpaRepository<Disponibilita, Long> {

}
