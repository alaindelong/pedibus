package com.example.pedibus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.service.AccompagnatoreDoCorsaService;

@Service
@Transactional
public class AccompagnatoreDoCorsaServiceImpl 
implements AccompagnatoreDoCorsaService{

	@Override
	public void assignCorsaAdAccompagnatore(Long corsoId, Long accompagnatoreId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UnAssignCorsaAdAccompagnatore(Long corsoId, Long accompagnatoreId) {
		// TODO Auto-generated method stub
		
	}

}
