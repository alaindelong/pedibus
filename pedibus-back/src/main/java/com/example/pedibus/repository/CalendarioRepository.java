package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pedibus.model.Calendario;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

}
