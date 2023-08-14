package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends 
JpaRepository<ConfirmationToken, Long> {
public ConfirmationToken findByToken(String token);
}
