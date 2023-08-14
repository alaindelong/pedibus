package com.example.pedibus.service;

import java.util.List;

import com.example.pedibus.model.PedibusUser;

public interface PedibusUserService{
public PedibusUser addPedibusUser(PedibusUser pedibusUser) throws Exception;
public PedibusUser findByUsername(String username);
public PedibusUser updatePedibusUser(PedibusUser pedibusUser, Long id) throws Exception;
public List<PedibusUser> addPedibusUsers(List<PedibusUser> pedibusUsers)throws Exception;
public List<PedibusUser> getAllPedibusUser();
public PedibusUser getPedibusUser(Long id);
public String enableUser(String username);
public boolean addOrRemoveAuthority(String username,String operation, String role);
}
