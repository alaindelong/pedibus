package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.model.Presenza;

public interface PresenzaService {

	public Presenza addPresenza(Presenza presenza);
	public Presenza addPresenza(String nomeLinea,String giorno,String nomeFermata,String nomeBambino,int direzione);
	public List<Presenza> getPresenze(String nomeLinea,String giorno,int direzione);
	public List<Presenza> addPresenze(List<Presenza> presenze);
}
