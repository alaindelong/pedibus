package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Agenzia;

@Repository
public interface AgenziaRepository extends JpaRepository<Agenzia, Long> {

}
