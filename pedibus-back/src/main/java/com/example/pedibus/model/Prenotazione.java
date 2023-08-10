package com.example.pedibus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//chi prenota
	private String nomePrenotatore;
	//per chi prenota
	private String nomeBambino;
	//quale linea or percorso
	private String nomeLinea;
	//quale giorno
	private String giorno;
	//per quale ora
	private String ora;
	//da quale fermata
	private String nomeFermataDiPartenza;
	//fino a
	private String nomeFermataDiArrivo;
	private int direzione; //0 per andata 1 per ritorno
}
