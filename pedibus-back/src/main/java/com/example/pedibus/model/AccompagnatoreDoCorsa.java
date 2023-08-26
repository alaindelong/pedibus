package com.example.pedibus.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccompagnatoreDoCorsa {
	@EmbeddedId
private KeyAccompagnatoreDoCorsa id;
}
