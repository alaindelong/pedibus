package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.dto.FermataDto;
import com.example.pedibus.dto.PercorsoDto;
import com.example.pedibus.mapper.PercorsoMapper;
import com.example.pedibus.model.Corsa;
import com.example.pedibus.model.Percorso;
import com.example.pedibus.repository.FermataRepository;
import com.example.pedibus.repository.PercorsoRepository;
import com.example.pedibus.service.FermataService;
import com.example.pedibus.service.PercorsoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PercorsoServiceImpl implements PercorsoService {
    @Autowired
	private PercorsoRepository percorsoRepository;
    @Autowired
    private FermataRepository fermataRepository;
    @Autowired
    private FermataService fermataService;
    @Autowired
    private PercorsoMapper percorsoMapper;
	@Override
	public List<Percorso> addPercorsi(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Percorso>> typeReference = new TypeReference<List<Percorso>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<Percorso> result = new ArrayList<Percorso>();
		try {
			List<Percorso> corsi = mapper.readValue(inputStream, typeReference);
			 result = percorsoRepository.saveAll(corsi);
			if(result != null)
		      System.out.println("percorsi saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save percorsi "+e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<Percorso> getPercorsi() {
		
		return percorsoRepository.findAll();
	}
	@Override
	public Percorso getPercorso(String nomeLinea) {
		
		return percorsoRepository.findByPercorsoLongNameIgnoreCase(nomeLinea);
	}

	@Override
	public PercorsoDto getDettagliPercorso(String nomeLinea){
		List<FermataDto> fermate = fermataRepository.findByPercorsoLongName(nomeLinea);
		Percorso p = getPercorso(nomeLinea);
		PercorsoDto result = percorsoMapper.toPercorsoDto(p);
		result.setFermateDiAndata(fermate.stream()
		       .filter(f -> f.getDirectionId()==0)
		       .collect(Collectors.toList()));
		result.setFermateDiRitorno(fermate
				.stream()
				.filter(f -> f.getDirectionId() == 1)
				.collect(Collectors.toList())
				);                
		return result;
	}

}
