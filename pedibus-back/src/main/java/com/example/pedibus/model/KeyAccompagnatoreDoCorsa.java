package com.example.pedibus.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyAccompagnatoreDoCorsa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long accompagnatoreId;
	private Long corsoId;
}
