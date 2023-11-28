package com.example.pedibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.model.Presenza;
import com.example.pedibus.service.PresenzaService;

@RestController
public class PresenzaController {

	@Autowired
	private PresenzaService presenzaService;
	
	@GetMapping("/presences/{nomeLinea}/{giorno}/{nomeFermata}/{nomeBambino}/{direzione}")
	public Presenza addPresenza(@PathVariable String nomeLinea,@PathVariable String giorno,@PathVariable String nomeFermata,@PathVariable String nomeBambino,@PathVariable int direzione) {
		return presenzaService.addPresenza(nomeLinea, giorno, nomeFermata, nomeBambino,direzione);
	}
}
