package com.example.pedibus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.dto.FermataDto;
import com.example.pedibus.dto.FermataPrenotataDto;
import com.example.pedibus.dto.PercorsoDto;
import com.example.pedibus.dto.PrenotazioneDto;
import com.example.pedibus.model.Prenotazione;
import com.example.pedibus.model.User;
import com.example.pedibus.repository.PrenotazioneRepository;
import com.example.pedibus.service.FermataService;
import com.example.pedibus.service.PercorsoService;
import com.example.pedibus.service.PrenotazioneService;

@Service
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	@Autowired
	private PercorsoService percorsoService;
	@Autowired
	private FermataService fermataService;
	@Override
	public Prenotazione addPrenotazione(Prenotazione prenotazione) {
		PercorsoDto pdto = percorsoService.getDettagliPercorso(prenotazione.getNomeLinea());
		if(pdto !=null) {
			List<FermataDto> fdto = pdto.getFermateDiAndata();
			int indexFermataDiPartenza=0, indexFermataDiArrivo=0, cont=0;
			for(FermataDto f:fdto) {
				System.out.println("=============");
				System.out.println("nome fermata "+f.getFermataName());
				System.out.println("fermata di partenza "+prenotazione.getNomeFermataDiPartenza());
				System.out.println("fermata di arrivo "+prenotazione.getNomeFermataDiArrivo());
				System.out.println("=============");
				if(f.getFermataName().equals(prenotazione.getNomeFermataDiPartenza()))
					indexFermataDiPartenza = cont;
				if(f.getFermataName().equals(prenotazione.getNomeFermataDiArrivo()))
					indexFermataDiArrivo = cont;
				 cont++;
			}
			if(indexFermataDiPartenza<indexFermataDiArrivo)
				prenotazione.setDirezione(0);
			else
				prenotazione.setDirezione(1);
		}
		return prenotazioneRepository.save(prenotazione);
	}

	@Override
	public List<PrenotazioneDto> getPrenotazioni(String nomeLinea, String data) {
		
		List<Prenotazione> lps = prenotazioneRepository
				                 .findByNomeLineaIgnoreCaseAndGiorno(nomeLinea, data);
		List<FermataDto> fdtos = fermataService.findByPercorsoLongName(nomeLinea);
		
		if(lps!=null)
			System.out.println("=====numbero prenotazioni "+lps.size());
		if(fdtos!=null)
			System.out.println("====numero fermate "+fdtos.size());
		List<PrenotazioneDto> pdtos = new ArrayList<PrenotazioneDto>();
		if(lps !=null) {
			PrenotazioneDto pdto = new PrenotazioneDto();
			for(Prenotazione p:lps) {
				
				pdto.setGiorno(p.getGiorno());
				pdto.setNomeLinea(p.getNomeLinea());
				if(p.getDirezione() == 0) {
					//prenotazioni andata
					for(FermataDto f:fdtos) {
						
						FermataPrenotataDto fpdto = new FermataPrenotataDto();
						fpdto.setFermataId(f.getFermataId());
						fpdto.setFermataName(f.getFermataName());
						//get user by nome or altro
						
						if(f.getFermataName().equals(p.getNomeFermataDiPartenza())) {
							//caricare
							if(fpdto.getCaricati()!=null)
								 fpdto.getCaricati().add(p.getNomeBambino());
							else fpdto.setCaricati(new ArrayList<String>(List.of( p.getNomeBambino())));
							if(pdto.getAndata()!=null) {
								int cont =0; boolean flag = false;
								for(FermataPrenotataDto fp:pdto.getAndata()) {
								if(fp.getFermataName().equals(fpdto.getFermataName())) {
									pdto.getAndata().get(cont).getCaricati().add(p.getNomeBambino());
									flag=true;
								}
								cont++;	
								}
								if(!flag)
								  pdto.getAndata().add(fpdto);
							}
							 else pdto.setAndata(new ArrayList<FermataPrenotataDto>(List.of(fpdto)));
						}
						if(f.getFermataName().equals(p.getNomeFermataDiArrivo())) {
							//lasciare
							if(fpdto.getLasciati()!=null)
								 fpdto.getLasciati().add(p.getNomeBambino());
								else fpdto.setLasciati(new ArrayList<String>(List.of( p.getNomeBambino())));
								if(pdto.getRitorno()!=null) {
									int cont =0; boolean flag = false;
									for(FermataPrenotataDto fp:pdto.getRitorno()) {
										if(fp.getFermataName().equals(fpdto.getFermataName())) {
											pdto.getRitorno().get(cont).getLasciati().add(p.getNomeBambino());
											flag=true;
										}
										cont++;	
										}
										if(!flag)
									pdto.getRitorno().add(fpdto);
								}
								 
								else pdto.setRitorno(new ArrayList<FermataPrenotataDto>(List.of(fpdto)));
						}
						
					}
				}else {
					//prenotazioni ritorno
					for(FermataDto f:fdtos) {
						FermataPrenotataDto fpdto = new FermataPrenotataDto();
						fpdto.setFermataId(f.getFermataId());
						fpdto.setFermataName(f.getFermataName());
						if(f.getFermataName().equals(p.getNomeFermataDiPartenza())) {
							//caricare
							if(fpdto.getCaricati()!=null)
								 fpdto.getCaricati().add(p.getNomeBambino());
							else fpdto.setCaricati(new ArrayList<String>(List.of( p.getNomeBambino())));
							if(pdto.getAndata()!=null) {
								int cont =0; boolean flag = false;
								for(FermataPrenotataDto fp:pdto.getAndata()) {
								if(fp.getFermataName().equals(fpdto.getFermataName())) {
									pdto.getAndata().get(cont).getCaricati().add(p.getNomeBambino());
									flag=true;
								}
								cont++;	
								}
								if(!flag)
								  pdto.getAndata().add(fpdto);
							}
							 else pdto.setAndata(new ArrayList<FermataPrenotataDto>(List.of(fpdto)));
						}
						if(f.getFermataName().equals(p.getNomeFermataDiArrivo())) {
							//lasciare
							if(fpdto.getLasciati()!=null)
								 fpdto.getLasciati().add(p.getNomeBambino());
								else fpdto.setLasciati(new ArrayList<String>(List.of( p.getNomeBambino())));
								if(pdto.getRitorno()!=null) {
									int cont =0; boolean flag = false;
									for(FermataPrenotataDto fp:pdto.getRitorno()) {
										if(fp.getFermataName().equals(fpdto.getFermataName())) {
											pdto.getRitorno().get(cont).getLasciati().add(p.getNomeBambino());
											flag=true;
										}
										cont++;	
										}
										if(!flag)
									pdto.getRitorno().add(fpdto);
								}
								 
								else pdto.setRitorno(new ArrayList<FermataPrenotataDto>(List.of(fpdto)));
						}
					}
				}
				
			}
			pdtos.add(pdto);
		}
		
		return pdtos;
	}

	@Override
	public List<Prenotazione> addPrenotazioni(List<Prenotazione> prenotazioni) {
		List<Prenotazione> result = new ArrayList<Prenotazione>();
		for(Prenotazione p:prenotazioni) {
			result.add(addPrenotazione(p));
		}
		return result;
	}

}
