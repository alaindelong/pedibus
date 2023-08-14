package com.example.pedibus.service;

public interface EmailService {
public boolean isEmailValid(String email);
public void send(String to, String subject, String text);
}
