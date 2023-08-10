package com.example.pedibus.dto;

import java.util.List;

import com.example.pedibus.model.Fermata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PercorsoDto {

	private Long id;
	private String percorsoId;
	private String agenziaId;
	private String percorsoShortName;
	private String percorsoLongName;
	private int percorsoType;
	private List<FermataDto> fermateDiAndata;
	private List<FermataDto> fermateDiRitorno;
}
