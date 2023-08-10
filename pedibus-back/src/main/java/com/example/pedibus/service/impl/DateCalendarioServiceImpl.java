package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Calendario;
import com.example.pedibus.model.DateCalendario;
import com.example.pedibus.repository.DateCalendarioRepository;
import com.example.pedibus.service.DateCalendarioService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class DateCalendarioServiceImpl implements DateCalendarioService{
	@Autowired
private DateCalendarioRepository dateCalendarioRepository;
	@Override
	public List<DateCalendario> addDateCalendario(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<DateCalendario>> typeReference = new TypeReference<List<DateCalendario>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<DateCalendario> result = new ArrayList<DateCalendario>();
		try {
			List<DateCalendario> calendari = mapper.readValue(inputStream, typeReference);
			 result = dateCalendarioRepository.saveAll(calendari);
			if(result != null)
		      System.out.println("date calendario saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save date calendario "+e.getMessage());
		}
		return result;
	}

}
