package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
public List<Prenotazione> findByNomeLineaIgnoreCaseAndGiorno(String nomeLinea,String data);
}
