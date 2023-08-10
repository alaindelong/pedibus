package com.example.pedibus.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneDto {
	   //quale linea or percorso
		private String nomeLinea;
		//quale giorno
		private String giorno;
		private List<FermataPrenotataDto> andata;
		private List<FermataPrenotataDto> ritorno;
}
