package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.AccompagnatoreDoCorsa;
import com.example.pedibus.model.KeyAccompagnatoreDoCorsa;

@Repository
public interface AccompagnatoreDoCorsaRepository 
extends JpaRepository<AccompagnatoreDoCorsa, KeyAccompagnatoreDoCorsa> {

}
