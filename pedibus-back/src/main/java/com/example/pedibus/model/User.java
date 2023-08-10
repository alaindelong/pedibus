package com.example.pedibus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String Cognome;
	private String email;
	//private String password;
}
