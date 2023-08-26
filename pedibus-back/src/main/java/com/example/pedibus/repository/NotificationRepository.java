package com.example.pedibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pedibus.model.Notification;

@Repository
public interface NotificationRepository
extends JpaRepository<Notification, Long>{

}
