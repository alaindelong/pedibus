package com.example.pedibus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disponibilita {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String giorno;
private String direzione;
@ManyToOne
private Accompagnatore accompagnatore;
}
