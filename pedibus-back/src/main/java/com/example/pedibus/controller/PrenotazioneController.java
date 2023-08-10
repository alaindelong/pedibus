package com.example.pedibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.dto.PrenotazioneDto;
import com.example.pedibus.dto.ResponsePrenotazioneDto;
import com.example.pedibus.model.Prenotazione;
import com.example.pedibus.service.PrenotazioneService;

@RestController
public class PrenotazioneController {
	@Autowired
private PrenotazioneService prenotazioneService;
	
	@PostMapping("/reservations/{nomeLinea}/{data}")
	public Prenotazione addPrenotazione(@PathVariable String nomeLinea,@PathVariable String data,
			                            @RequestBody Prenotazione prenotazione) {
		  prenotazione.setGiorno(data);
		  prenotazione.setNomeLinea(nomeLinea);
		return prenotazioneService.addPrenotazione(prenotazione);
	}
	@GetMapping("/reservations/{nomeLinea}/{data}")
	 public PrenotazioneDto getPrenotazioni(@PathVariable String nomeLinea,@PathVariable String data){
		return prenotazioneService.getPrenotazioni(nomeLinea, data);
	 }
	@PutMapping("/reservations/{nomeLinea}/{data}/{prenotazioneId}")
	public Prenotazione updatePrenotazione(@RequestBody Prenotazione prenotazione,@PathVariable String nomeLinea, @PathVariable String data,@PathVariable Long prenotazioneId) {
		return prenotazioneService.updatePrenotazione(prenotazione,nomeLinea,data, prenotazioneId);
	}
	@DeleteMapping("/reservations/{nomeLinea}/{data}/{prenotazioneId}")
	 public void deletePrenotazione(@PathVariable String nomeLinea, @PathVariable String data,@PathVariable Long prenotazioneId) {
		 prenotazioneService.deletePrenotazione(nomeLinea, data, prenotazioneId);
	 }
	@GetMapping("/reservations/{nomeLinea}/{data}/{prenotazioneId}")
	 public Prenotazione getPrenotazione(@PathVariable String nomeLinea, @PathVariable String data,@PathVariable Long prenotazioneId) {
		 return prenotazioneService.getPrenotazione(nomeLinea, data, prenotazioneId);
	 }
}
