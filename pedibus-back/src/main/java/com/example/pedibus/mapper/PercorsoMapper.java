package com.example.pedibus.mapper;

import org.springframework.stereotype.Component;

import com.example.pedibus.dto.PercorsoDto;
import com.example.pedibus.model.Percorso;

@Component
public class PercorsoMapper {

	public PercorsoDto toPercorsoDto(Percorso percorso) {
		PercorsoDto p = new PercorsoDto();
		if(percorso != null) {
		p.setId(percorso.getId());
		p.setAgenziaId(percorso.getAgenziaId());
		p.setPercorsoId(percorso.getPercorsoId());
		p.setPercorsoLongName(percorso.getPercorsoLongName());
		p.setPercorsoShortName(percorso.getPercorsoShortName());
		p.setPercorsoType(percorso.getPercorsoType());}
		return p;
	}
}
