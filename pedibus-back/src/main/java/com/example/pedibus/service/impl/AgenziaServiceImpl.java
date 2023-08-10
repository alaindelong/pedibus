package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Agenzia;
import com.example.pedibus.repository.AgenziaRepository;
import com.example.pedibus.service.AgenziaService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class AgenziaServiceImpl implements AgenziaService {
	
	@Autowired
	private AgenziaRepository agenziaRepository;

	@Override
	public List<Agenzia> addAgenzie(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Agenzia>> typeReference = new TypeReference<List<Agenzia>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<Agenzia> result = new ArrayList<Agenzia>();
		try {
			List<Agenzia> agenzie = mapper.readValue(inputStream, typeReference);
			 result = agenziaRepository.saveAll(agenzie);
			if(result != null)
		      System.out.println("agenzie saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save agenzia "+e.getMessage());
		}
		return result;
	}

}
