package com.example.pedibus.service;

import com.example.pedibus.model.ConfirmationToken;

public interface ConfirmationTokenService {
public ConfirmationToken savedToken(ConfirmationToken token);
public String confirmToken(String token) throws IllegalAccessException;
}
