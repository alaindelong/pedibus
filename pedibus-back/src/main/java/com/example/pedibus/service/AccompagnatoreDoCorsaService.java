package com.example.pedibus.service;

public interface AccompagnatoreDoCorsaService {

	void assignCorsaAdAccompagnatore(Long corsoId,Long accompagnatoreId);
	void UnAssignCorsaAdAccompagnatore(Long corsoId,Long accompagnatoreId);
}
