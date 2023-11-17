package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.model.Corsa;

public interface CorsaService {

	public List<Corsa> addCorse(String url);
	public Corsa getCorsa(Long corsoId);
}
