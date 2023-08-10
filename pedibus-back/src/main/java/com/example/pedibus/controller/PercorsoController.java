package com.example.pedibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedibus.dto.PercorsoDto;
import com.example.pedibus.model.Percorso;
import com.example.pedibus.service.PercorsoService;

@RestController
public class PercorsoController {
	@Autowired
private PercorsoService percorsoService;
	
	@GetMapping("/lines")
	public List<Percorso> getPercorsi(){
		return percorsoService.getPercorsi();
	}
	@GetMapping("/lines/{nomeLinea}")
	public PercorsoDto getPercorso(@PathVariable String nomeLinea){
		return percorsoService.getDettagliPercorso(nomeLinea);
	}
}
