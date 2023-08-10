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
public class Fermata {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fermataId;
	private String fermataName;
	private double fermataLat;
	private double fermataLon;
	private int fermataZoneId;
}
