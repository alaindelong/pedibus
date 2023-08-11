package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.PedibusUser;

@Repository
public interface PedibusUserRepository extends JpaRepository<PedibusUser, Long> {
	public PedibusUser findByUsername(String username);
}
