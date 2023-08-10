package com.example.pedibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pedibus.dto.FermataDto;
import com.example.pedibus.model.Fermata;

public interface FermataRepository extends JpaRepository<Fermata, Long> {
public Fermata findByFermataIdIgnoreCase(String fermataId);
@Query("select new com.example.pedibus.dto.FermataDto(fer.id, fer.fermataId,"
		+ "fer.fermataName,fer.fermataLat,fer.fermataLon,"
		+ "fer.fermataZoneId, c.directionId,"
		+ "ofer.fermataSequenza,ofer.oraArrivo,ofer.oraPartenza) from "
		+ "Percorso p "
		+ "join Corsa c on p.percorsoId = c.percorsoId "
		+ "join OreFermata ofer on c.corsoId = ofer.corsoId "
		+ "join Fermata fer on ofer.fermataId = fer.fermataId "
		+ "where UPPER(p.percorsoLongName) =UPPER(?1)")
public List<FermataDto> findByPercorsoLongName(String nomeLinea);
}
