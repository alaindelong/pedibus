package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Fermata;
import com.example.pedibus.model.OreFermata;
import com.example.pedibus.repository.OreFermataRepository;
import com.example.pedibus.service.OreFermataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class OreFermataServiceImpl implements OreFermataService {
	@Autowired
private OreFermataRepository oreFermataRepository;
	@Override
	public List<OreFermata> addOreFermate(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<OreFermata>> typeReference = new TypeReference<List<OreFermata>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<OreFermata> result = new ArrayList<OreFermata>();
		try {
			List<OreFermata> fermate = mapper.readValue(inputStream, typeReference);
			 result = oreFermataRepository.saveAll(fermate);
			if(result != null)
		      System.out.println("ore fermate saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save ore fermate "+e.getMessage());
		}
		return result;
	}

}
