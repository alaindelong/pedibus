package com.example.pedibus;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.pedibus.model.Accompagnatore;
import com.example.pedibus.model.Agenzia;
import com.example.pedibus.model.Passaggero;
import com.example.pedibus.model.PedibusUser;
import com.example.pedibus.model.Prenotazione;
import com.example.pedibus.model.Role;
import com.example.pedibus.model.User;
import com.example.pedibus.repository.RoleRepository;
import com.example.pedibus.security.ApplicationUserRole;
import com.example.pedibus.service.AgenziaService;
import com.example.pedibus.service.CalendarioService;
import com.example.pedibus.service.CorsaService;
import com.example.pedibus.service.DateCalendarioService;
import com.example.pedibus.service.FermataService;
import com.example.pedibus.service.OreFermataService;
import com.example.pedibus.service.PedibusUserService;
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
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public DateTimeFormatter dateFormat() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return dateTimeFormatter;
	}
	
	@Autowired
	public RoleRepository roleRepository;
	
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
			PrenotazioneService prenotazioneService,
			PedibusUserService pedibusUserService
			) {
		String urlAgenzie = "/data/agenzia.json";
		String urlCalendario = "/data/calendario.json";
		String urlCorsi = "/data/corsi.json";
		String urlDateCalendario = "/data/date_calendario.json";
		String urlFermate = "/data/fermate.json";
		String urlOreFermate = "/data/ore_fermata.json";
		String urlPercorsi = "/data/percorsi.json";
		//ROLES
		/*Set<Role> roles = new HashSet<Role>();
		roles = Stream.of(ApplicationUserRole.values())
		.map(v -> new Role(v.name()))
		.collect(Collectors.toSet());
		roleRepository.saveAll(roles);*/
		//USERS
		List<User> users = new ArrayList<>();
		List<PedibusUser> pUsers = new ArrayList<PedibusUser>();
		pUsers.add(new PedibusUser(1L,
				ApplicationUserRole.ADMIN.name(),
				Set.of(new Role(ApplicationUserRole.ADMIN.name())),
				"admin",
				"admin",
				"admin@gmail.com",
				"admin",
				true, true, true, true
					));
		pUsers.add(new PedibusUser(1L,
				ApplicationUserRole.SYSTEM_ADMIN.name(),
				Set.of(new Role(ApplicationUserRole.SYSTEM_ADMIN.name())),
				"admin",
				"sysadmin",
				"sysadmin@gmail.com",
				"sysadmin",
				true, true, true, true
					));
		for(int i=1;i<=5;i++) {
			pUsers.add(new Passaggero(1L,
					ApplicationUserRole.USER.name(),
					Set.of(new Role(ApplicationUserRole.USER.name())),
					"pass",
					"pass"+i,
					"pass"+i+"@gmail.com",
					"pass"+i,
					true, true, true, true
						));
		}
		Passaggero p =new Passaggero(1L,
				ApplicationUserRole.USER.name(),
				Set.of(new Role(ApplicationUserRole.USER.name())),
				"pass",
				"pass",
				"pass"+"@gmail.com",
				"pass",
				true, true, true, true
					);
		for(int i=0;i<5;i++) {
			pUsers.add(new Accompagnatore(1L,
					ApplicationUserRole.ACCOMPAGNATORE.name(),
					Set.of(new Role(ApplicationUserRole.USER.name()),
							new Role(ApplicationUserRole.ACCOMPAGNATORE.name())),
					"accomp",
					"accomp"+i,
					"accomp"+i+"@gmail.com",
					"accomp"+i,
					true, true, true, true
						));
		}
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
		pa1.setGiorno(LocalDate.now().format(dateFormat()));
		pa1.setNomeBambino("pass1");
		pa1.setNomePrenotatore("papa1");
		pa1.setNomeLinea("LIGNE_9518");
		pa1.setNomeFermataDiPartenza("Gare de poissy");
		pa1.setNomeFermataDiArrivo("Gare de SGL");
		pa1.setOra("09:00:00");
		
		prenotazioni.add(pa1);
		Prenotazione pr1 = new Prenotazione();
		pr1.setGiorno(LocalDate.now().format(dateFormat()));
		pr1.setNomeBambino("pass1");
		pr1.setNomePrenotatore("papa1");
		pr1.setNomeLinea("LIGNE_9518");
		pr1.setNomeFermataDiPartenza("Gare de SGL");
		pr1.setNomeFermataDiArrivo("Gare de poissy");
		pr1.setOra("14:00:00");
		prenotazioni.add(pr1);
		//papa3 prenota per il bambino3
				Prenotazione pa3 = new Prenotazione();
				pa3.setGiorno(LocalDate.now().format(dateFormat()));
				pa3.setNomeBambino("pass3");
				pa3.setNomePrenotatore("papa3");
				pa3.setNomeLinea("LIGNE_9518");
				pa3.setNomeFermataDiPartenza("Gare de SGL");
				pa3.setNomeFermataDiArrivo("Gare de Cergy le Haut");
				pa3.setOra("09:00:00");
				prenotazioni.add(pa3);
				Prenotazione pr3 = new Prenotazione();
				pr3.setGiorno(LocalDate.now().format(dateFormat()));
				pr3.setNomeBambino("pass3");
				pr3.setNomePrenotatore("papa3");
				pr3.setNomeLinea("LIGNE_9518");
				pr3.setNomeFermataDiPartenza("Gare de Cergy le Haut");
				pr3.setNomeFermataDiArrivo("Gare de poissy");
				pr3.setOra("15:00:00");
		    prenotazioni.add(pr3);
		//papa1 prenota per il bambino11
				Prenotazione pa11 = new Prenotazione();
				pa11.setGiorno(LocalDate.now().format(dateFormat()));
				pa11.setNomeBambino("pass4");
				pa11.setNomePrenotatore("papa1");
				pa11.setNomeLinea("LIGNE_H");
				pa11.setNomeFermataDiPartenza("Gare de Luzarches");
				pa11.setNomeFermataDiArrivo("Gare de Cergy");
				pa11.setOra("16:16:00");
				prenotazioni.add(pa11);
				Prenotazione pr11 = new Prenotazione();
				pr11.setGiorno(LocalDate.now().format(dateFormat()));
				pr11.setNomeBambino("pass4");
				pr11.setNomePrenotatore("papa1");
				pr11.setNomeLinea("LIGNE_H");
				pr11.setNomeFermataDiPartenza("Gare de Pontoise");
				pr11.setNomeFermataDiArrivo("Gare de Luzarches");
				pr11.setOra("18:20:00");
				prenotazioni.add(pr11);
			//papa2 prenota per il bambino2
				Prenotazione pa2 = new Prenotazione();
				pa2.setGiorno(LocalDate.now().format(dateFormat()));
				pa2.setNomeBambino("pass2");
				pa2.setNomePrenotatore("papa2");
				pa2.setNomeLinea("LIGNE_H");
				pa2.setNomeFermataDiPartenza("Gare de Luzarches");
				pa2.setNomeFermataDiArrivo("Gare de Pontoise");
				pa2.setOra("16:20:00");	
				prenotazioni.add(pa2);
				Prenotazione pa4 = new Prenotazione();
				pa4.setGiorno(LocalDate.now().format(dateFormat()));
				pa4.setNomeBambino("pass5");
				pa4.setNomePrenotatore("papa2");
				pa4.setNomeLinea("LIGNE_H");
				pa4.setNomeFermataDiPartenza("Gare de Pontoise");
				pa4.setNomeFermataDiArrivo("Gare de Cergy");
				pa4.setOra("16:20:00");	
				prenotazioni.add(pa4);
				Prenotazione pr2 = new Prenotazione();
				pr2.setGiorno(LocalDate.now().format(dateFormat()));
				pr2.setNomeBambino("pass2");
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
			pedibusUserService.addPedibusUsers(pUsers);
			pedibusUserService.addPedibusUser(p);
		};
	}

}
