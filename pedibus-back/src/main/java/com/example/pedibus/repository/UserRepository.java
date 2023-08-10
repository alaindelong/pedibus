package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
