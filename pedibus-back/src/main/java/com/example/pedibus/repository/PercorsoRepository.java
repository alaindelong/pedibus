package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Percorso;

@Repository
public interface PercorsoRepository extends JpaRepository<Percorso, Long> {

	public Percorso findByPercorsoLongNameIgnoreCase(String nomeLinea);
}
