package com.example.pedibus.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.dto.PrenotazioneDto;
import com.example.pedibus.model.Prenotazione;
import com.example.pedibus.model.Presenza;
import com.example.pedibus.repository.PresenzaRepository;
import com.example.pedibus.service.PrenotazioneService;
import com.example.pedibus.service.PresenzaService;

@Service
@Transactional
public class PresenzaServiceImpl implements PresenzaService {

	@Autowired
	private PresenzaRepository presenzaRepository;
	@Autowired
	private PrenotazioneService prenotazioneService;
	@Autowired
	private DateTimeFormatter dateFormat;
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

	@Override
	public Presenza addPresenza(String nomeLinea,String giorno, String nomeFermata, String nomeBambino,int direzione) {

		Presenza pp = presenzaRepository.findByNomeLineaIgnoreCaseAndGiornoAndNomeFermata(nomeLinea, giorno, nomeFermata);
		Presenza p = new Presenza();
		if(pp == null) {
			 
	         p.setGiorno(LocalDate.now().format(dateFormat));
	         p.setNomeFermata(nomeFermata);
	         p.setNomeLinea(nomeLinea);
	       //RESERVATIONS
	         List<Prenotazione> prenotazioni = prenotazioneService.getBasePrenotazioni(nomeLinea, LocalDate.now().format(dateFormat),direzione);
	         for(Prenotazione pe: prenotazioni) {
	         	if(pe.getNomeBambino().equalsIgnoreCase(nomeBambino)) {
	         		if(pe.getNomeFermataDiPartenza().equalsIgnoreCase(nomeFermata)) {
	             		//p.setDirezione(pe.getDirezione());
	             		p.setSaliti(Set.of(nomeBambino));
	             	}
	             	
	             	if(pe.getNomeFermataDiArrivo().equalsIgnoreCase(nomeFermata)) {
	             		//p.setDirezione(pe.getDirezione());
	             		p.setDiscesi(Set.of(nomeBambino));
	             	}
	         	}
	         	
	         }
	 		return presenzaRepository.save(p);
		}else {
			//RESERVATIONS
	        List<Prenotazione> prenotazioni = prenotazioneService.getBasePrenotazioni(nomeLinea, LocalDate.now().format(dateFormat),direzione);
	        for(Prenotazione pe: prenotazioni) {
	        	if(pe.getNomeBambino().equalsIgnoreCase(nomeBambino)) {
	        		if(pe.getNomeFermataDiPartenza().equalsIgnoreCase(nomeFermata)) {
	            		
	            		pp.getSaliti().add(nomeBambino);
	            	}
	            	
	            	if(pe.getNomeFermataDiArrivo().equalsIgnoreCase(nomeFermata)) {
	            		
	            		pp.getDiscesi().add(nomeBambino);
	            	}
	        	}
	        	
	        }
			
			return pp;
		}
		
         
	}

}
