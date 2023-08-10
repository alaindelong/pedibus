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
public class Corsa {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String corsoId;
	private String percorsoId;
	private String servizioId;
	private String shapeId;
	private int directionId; //0 per andata et 1 per il ritorno
	private String nomeCapoLinea;
}
