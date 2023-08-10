package com.example.pedibus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FermataDto {
	private Long id;
	private String fermataId;
	private String fermataName;
	private double fermataLat;
	private double fermataLon;
	private int fermataZoneId;
	private int directionId;
	private int fermataSequenza;
	private String oraArrivo;
	private String oraPartenza;
}
