package com.example.pedibus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Presenza;
import com.example.pedibus.repository.PresenzaRepository;
import com.example.pedibus.service.PresenzaService;

@Service
@Transactional
public class PresenzaServiceImpl implements PresenzaService {

	@Autowired
	private PresenzaRepository presenzaRepository;
	@Override
	public Presenza addPresenza(Presenza presenza) {
		
		return presenzaRepository.save(presenza);
	}

	@Override
	public List<Presenza> getPresenze(String nomeLinea, String giorno, int direzione) {
		
		return presenzaRepository.findByNomeLineaIgnoreCaseAndGiornoAndDirezione(nomeLinea, giorno, direzione);
	}

	@Override
	public List<Presenza> addPresenze(List<Presenza> presenze) {
		
		return presenzaRepository.saveAll(presenze);
	}

}
