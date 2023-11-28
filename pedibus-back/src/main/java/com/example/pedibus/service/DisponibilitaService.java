package com.example.pedibus.service;

import com.example.pedibus.model.Disponibilita;

public interface DisponibilitaService {

	Disponibilita addDisponibilita(Disponibilita disponibilita,String username);
	void deleteDisponibilita(Long id);
}
