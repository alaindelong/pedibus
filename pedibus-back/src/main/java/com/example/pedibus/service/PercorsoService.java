package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.dto.PercorsoDto;
import com.example.pedibus.model.Percorso;

public interface PercorsoService {
public List<Percorso> addPercorsi(String url);
public List<Percorso> getPercorsi();
public Percorso getPercorso(String nomeLinea);
public PercorsoDto getDettagliPercorso(String nomeLinea);
}
