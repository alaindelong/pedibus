package com.example.pedibus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.AccompagnatoreDoCorsa;
import com.example.pedibus.model.Corsa;
import com.example.pedibus.model.KeyAccompagnatoreDoCorsa;
import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.repository.AccompagnatoreDoCorsaRepository;
import com.example.pedibus.repository.CorsaRepository;
import com.example.pedibus.repository.PedibusUserRepository;
import com.example.pedibus.service.AccompagnatoreDoCorsaService;

@Service
@Transactional
public class AccompagnatoreDoCorsaServiceImpl 
implements AccompagnatoreDoCorsaService{
	
	@Autowired
	private PedibusUserRepository pedibusUserRepository;
	@Autowired
	private CorsaRepository corsaRepository;
	@Autowired
	private AccompagnatoreDoCorsaRepository accompagnatoreDoCorsaRepository;

	@Override
	public void assignCorsaAdAccompagnatore(Long corsoId, Long accompagnatoreId) {
		PedibusUser p = pedibusUserRepository.getReferenceById(accompagnatoreId);
		Corsa c = corsaRepository.getReferenceById(corsoId);
		if(p != null && c != null) {
			KeyAccompagnatoreDoCorsa key = new KeyAccompagnatoreDoCorsa(accompagnatoreId, corsoId);
			AccompagnatoreDoCorsa a = new AccompagnatoreDoCorsa(key);
			AccompagnatoreDoCorsa saved =accompagnatoreDoCorsaRepository.save(a);
			if(saved == null)
				throw new IllegalStateException("cannot assign corsa to accompagnatore");
		}
	}

	@Override
	public void UnAssignCorsaAdAccompagnatore(Long corsoId, Long accompagnatoreId) {
		AccompagnatoreDoCorsa a = accompagnatoreDoCorsaRepository.findByKeyAccompagnatoreDoCorsa(corsoId, accompagnatoreId);
		if(a != null) {
			accompagnatoreDoCorsaRepository.deleteByKeyAccompagnatoreDoCorsa(corsoId, accompagnatoreId);
		}
		
	}

}
