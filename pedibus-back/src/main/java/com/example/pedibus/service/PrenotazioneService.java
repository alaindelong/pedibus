package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.dto.PrenotazioneDto;
import com.example.pedibus.model.Prenotazione;

public interface PrenotazioneService {
 public Prenotazione addPrenotazione(Prenotazione prenotazione);
 public List<PrenotazioneDto> getPrenotazioni(String nomeLinea,String data);
 public List<Prenotazione> addPrenotazioni(List<Prenotazione> prenotazioni);
}
