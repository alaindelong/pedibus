package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.AccompagnatoreDoCorsa;
import com.example.pedibus.model.KeyAccompagnatoreDoCorsa;

@Repository
public interface AccompagnatoreDoCorsaRepository 
extends JpaRepository<AccompagnatoreDoCorsa, KeyAccompagnatoreDoCorsa> {

	AccompagnatoreDoCorsa getById(
			KeyAccompagnatoreDoCorsa id);
	@Query("select ac from AccompagnatoreDoCorsa ac "
			+ "where ac.id.corsoId=?1 "
			+ "and ac.id.accompagnatoreId=?2")
	AccompagnatoreDoCorsa findByKeyAccompagnatoreDoCorsa(Long corsoId,
			Long AccompagnatoreId);
	@Query("delete from AccompagnatoreDoCorsa ac "
			+ "where ac.id.corsoId=?1 and ac.id.accompagnatoreId=?2" )
	void deleteByKeyAccompagnatoreDoCorsa(Long corsoId,
			Long AccompagnatoreId);
}
