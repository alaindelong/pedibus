package com.example.pedibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.dto.PrenotazioneDto;
import com.example.pedibus.dto.ResponsePrenotazioneDto;
import com.example.pedibus.model.Prenotazione;
import com.example.pedibus.service.PrenotazioneService;

@RestController
public class PrenotazioneController {
	@Autowired
private PrenotazioneService prenotazioneService;
	
	public Prenotazione addPrenotazione(Prenotazione prenotazione) {
		return prenotazioneService.addPrenotazione(prenotazione);
	}
	@GetMapping("/reservations/{nomeLinea}/{data}")
	 public ResponsePrenotazioneDto getPrenotazioni(@PathVariable String nomeLinea,@PathVariable String data){
		ResponsePrenotazioneDto response = new ResponsePrenotazioneDto();
		response.setPrenotazioni(prenotazioneService.getPrenotazioni(nomeLinea, data));
		return response;
	 }
}
