package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Disponibilita;

@Repository
public interface DisponibilitaRepository 
extends JpaRepository<Disponibilita, Long> {
	@Query("select d from Disponibilita d where d.accompagnatore.id=?1")
List<Disponibilita> findByAccompagnatoreId(Long accompagnatoreId);
}
