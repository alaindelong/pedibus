package com.example.pedibus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.pedibus.model.Agenzia;
import com.example.pedibus.model.Prenotazione;
import com.example.pedibus.model.User;
import com.example.pedibus.service.AgenziaService;
import com.example.pedibus.service.CalendarioService;
import com.example.pedibus.service.CorsaService;
import com.example.pedibus.service.DateCalendarioService;
import com.example.pedibus.service.FermataService;
import com.example.pedibus.service.OreFermataService;
import com.example.pedibus.service.PercorsoService;
import com.example.pedibus.service.PrenotazioneService;
import com.example.pedibus.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class PedibusApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedibusApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(
			AgenziaService agenziaService,
			CalendarioService calendarioService,
			CorsaService corsaService,
			DateCalendarioService dateCalendarioService,
			FermataService fermataService,
			OreFermataService oreFermataService,
			PercorsoService percorsoService,
			UserService userService,
			PrenotazioneService prenotazioneService
			) {
		String urlAgenzie = "/data/agenzia.json";
		String urlCalendario = "/data/calendario.json";
		String urlCorsi = "/data/corsi.json";
		String urlDateCalendario = "/data/date_calendario.json";
		String urlFermate = "/data/fermate.json";
		String urlOreFermate = "/data/ore_fermata.json";
		String urlPercorsi = "/data/percorsi.json";
		//USERS
		List<User> users = new ArrayList<>();
		User papa1 = new User();
		papa1.setCognome("papa1");papa1.setNome("papa1");papa1.setEmail("papa1@gmail.com");
		users.add(papa1);
		
		User papa2 = new User();
		papa2.setCognome("papa2");papa2.setNome("papa2");papa2.setEmail("papa2@gmail.com");
		users.add(papa2);
		
		User papa3 = new User();
		papa3.setCognome("papa3");papa3.setNome("papa3");papa3.setEmail("papa3@gmail.com");
		users.add(papa3);
		
		User bambino1 = new User();bambino1.setCognome("bambino1");
		bambino1.setNome("bambino1");
		bambino1.setEmail("papa1@gmail.com");
		users.add(bambino1);
		
		User bambino12 = new User();
		bambino12.setCognome("bambino12");bambino12.setNome("bambino12");
		bambino12.setEmail("papa1@gmail.com");
		users.add(bambino12);
		
		User bambino2 = new User();
		bambino2.setCognome("bambino2");bambino2.setNome("bambino2");
		bambino2.setEmail("papa2@gmail.com");
		users.add(bambino2);
		
		User bambino3 = new User();
		bambino3.setCognome("bambino3");bambino3.setNome("bambino3");
		bambino3.setEmail("papa3@gmail.com");
		users.add(bambino3);
		//PRENOTAZIONI
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		//papa1 prenota per il bambino1
		Prenotazione pa1 = new Prenotazione();
		pa1.setGiorno("20230702");
		pa1.setNomeBambino("bambino1");
		pa1.setNomePrenotatore("papa1");
		pa1.setNomeLinea("LIGNE_9518");
		pa1.setNomeFermataDiPartenza("Gare de poissy");
		pa1.setNomeFermataDiArrivo("Gare de Cergy le Haut");
		pa1.setOra("09:00:00");
		
		prenotazioni.add(pa1);
		Prenotazione pr1 = new Prenotazione();
		pr1.setGiorno("20230702");
		pr1.setNomeBambino("bambino1");
		pr1.setNomePrenotatore("papa1");
		pr1.setNomeLinea("LIGNE_9518");
		pr1.setNomeFermataDiPartenza("Gare de Cergy le Haut");
		pr1.setNomeFermataDiArrivo("Gare de poissy");
		pr1.setOra("14:00:00");
		prenotazioni.add(pr1);
		//papa3 prenota per il bambino3
				Prenotazione pa3 = new Prenotazione();
				pa3.setGiorno("20230702");
				pa3.setNomeBambino("bambino3");
				pa3.setNomePrenotatore("papa3");
				pa3.setNomeLinea("LIGNE_9518");
				pa3.setNomeFermataDiPartenza("Gare de poissy");
				pa3.setNomeFermataDiArrivo("Gare de Cergy le Haut");
				pa3.setOra("09:00:00");
				prenotazioni.add(pa3);
				Prenotazione pr3 = new Prenotazione();
				pr3.setGiorno("20230702");
				pr3.setNomeBambino("bambino3");
				pr3.setNomePrenotatore("papa3");
				pr3.setNomeLinea("LIGNE_9518");
				pr3.setNomeFermataDiPartenza("Gare de Cergy le Haut");
				pr3.setNomeFermataDiArrivo("Gare de poissy");
				pr3.setOra("15:00:00");
		    prenotazioni.add(pr3);
		//papa1 prenota per il bambino11
				Prenotazione pa11 = new Prenotazione();
				pa11.setGiorno("20230702");
				pa11.setNomeBambino("bambino11");
				pa11.setNomePrenotatore("papa1");
				pa11.setNomeLinea("LIGNE_H");
				pa11.setNomeFermataDiPartenza("Gare de Luzarches");
				pa11.setNomeFermataDiArrivo("Gare de Cergy");
				pa11.setOra("16:16:00");
				prenotazioni.add(pa11);
				Prenotazione pr11 = new Prenotazione();
				pr11.setGiorno("20230702");
				pr11.setNomeBambino("bambino11");
				pr11.setNomePrenotatore("papa1");
				pr11.setNomeLinea("LIGNE_H");
				pr11.setNomeFermataDiPartenza("Gare de Pontoise");
				pr11.setNomeFermataDiArrivo("Gare de Luzarches");
				pr11.setOra("18:20:00");
				prenotazioni.add(pr11);
			//papa2 prenota per il bambino2
				Prenotazione pa2 = new Prenotazione();
				pa2.setGiorno("20230702");
				pa2.setNomeBambino("bambino2");
				pa2.setNomePrenotatore("papa2");
				pa2.setNomeLinea("LIGNE_H");
				pa2.setNomeFermataDiPartenza("Gare de Luzarches");
				pa2.setNomeFermataDiArrivo("Gare de Pontoise");
				pa2.setOra("16:20:00");	
				prenotazioni.add(pa2);
				Prenotazione pa4 = new Prenotazione();
				pa4.setGiorno("20230702");
				pa4.setNomeBambino("bambino4");
				pa4.setNomePrenotatore("papa2");
				pa4.setNomeLinea("LIGNE_H");
				pa4.setNomeFermataDiPartenza("Gare de Pontoise");
				pa4.setNomeFermataDiArrivo("Gare de Cergy");
				pa4.setOra("16:20:00");	
				prenotazioni.add(pa4);
				Prenotazione pr2 = new Prenotazione();
				pr2.setGiorno("20230702");
				pr2.setNomeBambino("bambino2");
				pr2.setNomePrenotatore("papa2");
				pr2.setNomeLinea("LIGNE_H");
				pr2.setNomeFermataDiPartenza("Gare de Cergy");
				pr2.setNomeFermataDiArrivo("Gare de Pontoise");
				pr2.setOra("19:20:00");
		       prenotazioni.add(pr2);
		   
		return args ->{
			agenziaService.addAgenzie(urlAgenzie);
			calendarioService.addCalendario(urlCalendario);
			corsaService.addCorse(urlCorsi);
			dateCalendarioService.addDateCalendario(urlDateCalendario);
			fermataService.addFermate(urlFermate);
			oreFermataService.addOreFermate(urlOreFermate);
			percorsoService.addPercorsi(urlPercorsi);
			userService.addUsers(users);
			prenotazioneService.addPrenotazioni(prenotazioni);
		};
	}

}
