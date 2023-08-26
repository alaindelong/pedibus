package com.example.pedibus.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @ManyToMany(mappedBy = "notifications")
    @JsonIgnore
    private Set<PedibusUser> users;
    private Long corsoId;
    private String description;
}
