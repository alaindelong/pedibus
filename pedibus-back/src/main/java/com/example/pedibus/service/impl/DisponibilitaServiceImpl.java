package com.example.pedibus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.Accompagnatore;
import com.example.pedibus.model.Disponibilita;
import com.example.pedibus.repository.DisponibilitaRepository;
import com.example.pedibus.repository.PedibusUserRepository;
import com.example.pedibus.service.DisponibilitaService;

@Service
@Transactional
public class DisponibilitaServiceImpl
implements DisponibilitaService{
	
	@Autowired
	private DisponibilitaRepository disponibilitaRepository;
	@Autowired
	private PedibusUserRepository pedibusUserRepository;
	 

	@Override
	public Disponibilita addDisponibilita(Disponibilita disponibilita,String username) {
		Accompagnatore a = (Accompagnatore) pedibusUserRepository.findByUsername(username);
		disponibilita.setAccompagnatore(a);
		return disponibilitaRepository.save(disponibilita);
	}

	@Override
	public void deleteDisponibilita(Long id) {
		Disponibilita d =disponibilitaRepository.getReferenceById(id);
		d.setAccompagnatore(null);
		disponibilitaRepository.delete(d);
	}

}
