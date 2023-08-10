package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.dto.FermataDto;
import com.example.pedibus.model.Fermata;

public interface FermataService {
public List<Fermata> addFermate(String url);
public List<FermataDto> findByPercorsoLongName(String nomeLinea);
}
