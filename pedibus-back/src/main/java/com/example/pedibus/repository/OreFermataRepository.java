package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pedibus.model.OreFermata;

public interface OreFermataRepository extends JpaRepository<OreFermata, Long> {
public List<OreFermata> findByCorsoIdIgnoreCase(String corsoId);
}
