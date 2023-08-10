package com.example.pedibus.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePrenotazioneDto {

	private List<PrenotazioneDto> prenotazioni;
}
