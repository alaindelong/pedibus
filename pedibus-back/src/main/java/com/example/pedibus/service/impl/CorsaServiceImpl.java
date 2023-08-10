package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Corsa;
import com.example.pedibus.model.DateCalendario;
import com.example.pedibus.repository.CorsaRepository;
import com.example.pedibus.service.CorsaService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class CorsaServiceImpl implements CorsaService {
	@Autowired
	private CorsaRepository corsaRepository;
	@Override
	public List<Corsa> addCorse(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Corsa>> typeReference = new TypeReference<List<Corsa>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<Corsa> result = new ArrayList<Corsa>();
		try {
			List<Corsa> corsi = mapper.readValue(inputStream, typeReference);
			 result = corsaRepository.saveAll(corsi);
			if(result != null)
		      System.out.println("corsi saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save corsi "+e.getMessage());
		}
		return result;
	}

}
