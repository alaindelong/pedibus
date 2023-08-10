package com.example.pedibus.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.dto.FermataDto;
import com.example.pedibus.model.Corsa;
import com.example.pedibus.model.Fermata;
import com.example.pedibus.model.OreFermata;
import com.example.pedibus.model.Percorso;
import com.example.pedibus.repository.CorsaRepository;
import com.example.pedibus.repository.FermataRepository;
import com.example.pedibus.repository.OreFermataRepository;
import com.example.pedibus.repository.PercorsoRepository;
import com.example.pedibus.service.FermataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class FermataServiceImpl implements FermataService {
	@Autowired
private FermataRepository fermataRepository;
	@Autowired
private OreFermataRepository oreFermataRepository;
	@Autowired
	private CorsaRepository corsaRepository;
	@Autowired
	private PercorsoRepository percorsoRepository;
	@Override
	public List<Fermata> addFermate(String url) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Fermata>> typeReference = new TypeReference<List<Fermata>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(url);
		List<Fermata> result = new ArrayList<Fermata>();
		try {
			List<Fermata> fermate = mapper.readValue(inputStream, typeReference);
			 result = fermataRepository.saveAll(fermate);
			if(result != null)
		      System.out.println("fermate saved!!!");
			
		} catch(IOException e) {
			System.out.println("unable to save fermate "+e.getMessage());
		}
		return result;
	}
	@Override
	public List<FermataDto> findByPercorsoLongName(String nomeLinea) {
		Percorso p =percorsoRepository.findByPercorsoLongNameIgnoreCase(nomeLinea);
		/*List<Corsa> corse = new ArrayList<Corsa>();
		List<OreFermata> oreFermate = new ArrayList<>();
		Fermata fermata = new Fermata();
		List<FermataDto> fDtos = new ArrayList<FermataDto>();
		if(p!=null)
		 corse=corsaRepository.findByPercorsoIdIgnoreCase(p.getPercorsoId());
		for(Corsa c:corse) {
			oreFermate = oreFermataRepository.findByCorsoIdIgnoreCase(c.getCorsoId());
			for(OreFermata o:oreFermate) {
				fermata = fermataRepository.findByFermataIdIgnoreCase(o.getFermataId());
				FermataDto fdto = new FermataDto();
				fdto.setDirectionId(c.getDirectionId());
				fdto.setFermataId(fermata.getFermataId());
				fdto.setFermataLat(fermata.getFermataLat());
				fdto.setFermataLon(fermata.getFermataLon());
				fdto.setFermataName(fermata.getFermataName());
				fdto.setFermataSequenza(o.getFermataSequenza());
				fdto.setFermataZoneId(fermata.getFermataZoneId());
				fdto.setOraArrivo(o.getOraArrivo());
				fdto.setOraPartenza(o.getOraPartenza());
				fdto.setId(fermata.getId());
				fDtos.add(fdto);
			}
		}
		
		return fDtos;*/
		return fermataRepository.findByPercorsoLongName(nomeLinea);
	}

}
