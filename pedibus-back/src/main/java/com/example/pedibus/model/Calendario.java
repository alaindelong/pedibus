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
public class Calendario {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String servizioId;
	private int lunedi;
	private int martedi;
	private int mercoledi;
	private int giovedi;
	private int venerdi;
	private int sabato;
	private int domenica;
	private String dataInizioServizio;
	private String dataFineServizio;
}
