package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pedibus.model.Corsa;

public interface CorsaRepository extends JpaRepository<Corsa, Long> {

	public List<Corsa> findByPercorsoIdIgnoreCase(String percorsoId);
}
