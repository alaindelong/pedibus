package com.example.pedibus.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Presenza {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	private String nomeLinea;
	private String giorno;
	private String nomeFermata;
	private int direzione; //0 or 1
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "caricati", joinColumns =@JoinColumn(name = "caricato_id"))
	@Column(name = "caricato")
	private Set<String> saliti;
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "discesi", joinColumns = @JoinColumn(name = "lasciato_id"))
	@Column(name = "disceso")
	private Set<String> discesi;
}
