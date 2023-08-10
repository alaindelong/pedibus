package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Agenzia;
import com.example.pedibus.model.Calendario;
import com.example.pedibus.repository.CalendarioRepository;
import com.example.pedibus.service.CalendarioService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class CalendarioServiceImpl implements CalendarioService {
	@Autowired
private CalendarioRepository calendarioRepository;
	@Override
	public List<Calendario> addCalendario(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Calendario>> typeReference = new TypeReference<List<Calendario>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<Calendario> result = new ArrayList<Calendario>();
		try {
			List<Calendario> calendari = mapper.readValue(inputStream, typeReference);
			 result = calendarioRepository.saveAll(calendari);
			if(result != null)
		      System.out.println("calendari saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save calendari "+e.getMessage());
		}
		return result;
	}

}
