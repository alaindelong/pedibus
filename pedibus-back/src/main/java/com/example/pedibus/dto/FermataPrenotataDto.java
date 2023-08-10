package com.example.pedibus.dto;

import java.util.List;

import com.example.pedibus.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FermataPrenotataDto {
	private String fermataId;
	private String fermataName;
	private List<String> caricati;
	private List<String> lasciati;
}
