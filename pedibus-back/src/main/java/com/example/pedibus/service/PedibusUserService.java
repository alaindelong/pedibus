package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.model.PedibusUser;

public interface PedibusUserService{
public PedibusUser addPedibusUser(PedibusUser pedibusUser) throws Exception;
public PedibusUser findByUsername(String username);
public PedibusUser updatePedibusUser(PedibusUser pedibusUser, Long id) throws Exception;
public List<PedibusUser> addPedibusUsers(List<PedibusUser> pedibusUsers);
public List<PedibusUser> getAllPedibusUser();
}
