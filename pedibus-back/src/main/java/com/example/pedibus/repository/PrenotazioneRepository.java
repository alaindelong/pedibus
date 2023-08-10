package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
public List<Prenotazione> findByNomeLineaIgnoreCaseAndGiorno(String nomeLinea,String data);
public Prenotazione findByNomeLineaIgnoreCaseAndGiornoAndId(String nomeLinea,String data,Long prenotazioneId);
public void deleteByNomeLineaIgnoreCaseAndGiornoAndId(String nomeLinea,String data,Long prenotazioneId);
@Query("select p from Prenotazione p where UPPER(p.nomeLinea)=UPPER(?1) and p.giorno=?2 and p.id=?3")
public Prenotazione findByNomeLineaAndGiornoAndId(String nomeLinea,String data, Long prenotazioneId);
}
