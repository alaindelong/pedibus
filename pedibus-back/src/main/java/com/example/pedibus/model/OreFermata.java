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
public class OreFermata {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String corsoId;
	private String oraArrivo;
	private String oraPartenza;
	private String fermataId;
	private int fermataSequenza;
	private String destinazione;
}
