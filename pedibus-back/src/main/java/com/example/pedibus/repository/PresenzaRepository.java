package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Presenza;

@Repository
public interface PresenzaRepository extends JpaRepository<Presenza, Long> {

public List<Presenza> findByNomeLineaIgnoreCaseAndGiornoAndDirezione(String nomeLinea,String giorno,int direzione);
public Presenza findByNomeLineaIgnoreCaseAndGiornoAndNomeFermata(String nomeLinea,String giorno, String nomeFermata);
}
