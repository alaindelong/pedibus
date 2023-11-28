package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.dto.PrenotazioneDto;
import com.example.pedibus.model.Prenotazione;

public interface PrenotazioneService {
 public Prenotazione addPrenotazione(Prenotazione prenotazione);
 public PrenotazioneDto getPrenotazioni(String nomeLinea,String data);
 public List<Prenotazione> getBasePrenotazioni(String nomeLinea,String data,int direzione);
 public List<Prenotazione> addPrenotazioni(List<Prenotazione> prenotazioni);
 public Prenotazione updatePrenotazione(Prenotazione prenotazione,String nomeLinea, String data, Long prenotazioneId);
 public void deletePrenotazione(String nomeLinea, String data,Long prenotazioneId);
 public Prenotazione getPrenotazione(String nomeLinea, String data,Long prenotazioneId);
}
