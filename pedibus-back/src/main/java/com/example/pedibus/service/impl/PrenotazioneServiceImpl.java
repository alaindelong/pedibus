package com.example.pedibus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public PrenotazioneDto getPrenotazioni(String nomeLinea, String data) {
		
		List<Prenotazione> lps = prenotazioneRepository
				                 .findByNomeLineaIgnoreCaseAndGiorno(nomeLinea, data);
		List<FermataDto> fdtos = fermataService.findByPercorsoLongName(nomeLinea);
		List<FermataDto> fermateAndata = new ArrayList<FermataDto>();
		List<FermataDto> fermateRitorno = new ArrayList<FermataDto>();
		if(lps!=null)
			System.out.println("=====numbero prenotazioni "+lps.size());
		if(fdtos!=null) {
			System.out.println("====numero fermate "+fdtos.size());
			fermateAndata = fdtos.stream()
			     .filter(f -> f.getDirectionId() ==0)
			     .collect(Collectors.toList());
			fermateRitorno = fdtos.stream()
				     .filter(f -> f.getDirectionId() ==1)
				     .collect(Collectors.toList());
		}
			
		PrenotazioneDto pdto = new PrenotazioneDto();
		//je recupère la liste des fermates andata
		Map<String,List<String>> daCaricareAndata = new HashMap<String, List<String>>();
		Map<String,List<String>> daLasciareAndata = new HashMap<String, List<String>>();
		//je recupère la liste des fermates ritorno
		Map<String,List<String>> daCaricareRitorno = new HashMap<String, List<String>>();
		Map<String,List<String>> daLasciareRitorno = new HashMap<String, List<String>>();
		if(lps !=null) {
			
			for(Prenotazione p:lps) {
				
				pdto.setGiorno(p.getGiorno());
				pdto.setNomeLinea(p.getNomeLinea());
				if(p.getDirezione() == 0) {
					//prenotazioni andata
					
					for(FermataDto fa:fermateAndata) {
					if(fa.getFermataName().equals(p.getNomeFermataDiPartenza())) {
						//caricare
						if(daCaricareAndata == null) {
							//j'insère
							daCaricareAndata.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
						}else {
							//je check d'abord la clé
							if(daCaricareAndata.containsKey(fa.getFermataName()))
								daCaricareAndata.get(fa.getFermataName()).add(p.getNomeBambino());
							else
								daCaricareAndata.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
						}
					}
					if(fa.getFermataName().equals(p.getNomeFermataDiArrivo())) {
						//lasciare
						if(daLasciareAndata == null) {
							//j'insère
							daLasciareAndata.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
						}else {
							//je check d'abord la clé
							if(daLasciareAndata.containsKey(fa.getFermataName()))
								daLasciareAndata.get(fa.getFermataName()).add(p.getNomeBambino());
							else
								daLasciareAndata.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
						}
					}
					}
						//je fais la suite ici
				}else {
					//prenotazioni ritorno
					
					for(FermataDto fa:fermateRitorno) {
						if(fa.getFermataName().equals(p.getNomeFermataDiPartenza())) {
							//caricare
							if(daCaricareRitorno == null) {
								//j'insère
								daCaricareRitorno.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
							}else {
								//je check d'abord la clé
								if(daCaricareRitorno.containsKey(fa.getFermataName()))
									daCaricareRitorno.get(fa.getFermataName()).add(p.getNomeBambino());
								else
									daCaricareRitorno.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
							}
						}
						if(fa.getFermataName().equals(p.getNomeFermataDiArrivo())) {
							//lasciare
							if(daLasciareRitorno == null) {
								//j'insère
								daLasciareRitorno.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
							}else {
								//je check d'abord la clé
								if(daLasciareRitorno.containsKey(fa.getFermataName()))
									daLasciareRitorno.get(fa.getFermataName()).add(p.getNomeBambino());
								else
									daLasciareRitorno.put(fa.getFermataName(), new ArrayList<String>(List.of(p.getNomeBambino())));
							}
						}
						}
							
				}
				
			}
			//la suite
			 List<FermataPrenotataDto> andata = new ArrayList<FermataPrenotataDto>();
			 List<FermataPrenotataDto> ritorno = new ArrayList<FermataPrenotataDto>();
			 for(FermataDto fa:fermateAndata) {
				 FermataPrenotataDto fp = new FermataPrenotataDto();
				 fp.setFermataId(fa.getFermataId());
				 fp.setFermataName(fa.getFermataName());
				 fp.setCaricati( daCaricareAndata.get(fa.getFermataName()));
				 fp.setLasciati(daLasciareAndata.get(fa.getFermataName()));
				 andata.add(fp);
			 }
			 for(FermataDto fr:fermateRitorno) {
				 FermataPrenotataDto fp = new FermataPrenotataDto();
				 fp.setFermataId(fr.getFermataId());
				 fp.setFermataName(fr.getFermataName());
				 fp.setCaricati( daCaricareRitorno.get(fr.getFermataName()));
				 fp.setLasciati(daLasciareRitorno.get(fr.getFermataName()));
				 ritorno.add(fp);
			 }
			 pdto.setAndata(andata);
			 pdto.setRitorno(ritorno);
			 
		}
		return pdto;
	}

	@Override
	public List<Prenotazione> addPrenotazioni(List<Prenotazione> prenotazioni) {
		List<Prenotazione> result = new ArrayList<Prenotazione>();
		for(Prenotazione p:prenotazioni) {
			result.add(addPrenotazione(p));
		}
		return result;
	}

	@Override
	public Prenotazione updatePrenotazione(Prenotazione prenotazione,String nomeLinea,String data, Long prenotazioneId) {
		Prenotazione p = prenotazioneRepository.findByNomeLineaAndGiornoAndId(nomeLinea,data,prenotazioneId);
		if(p !=null) {
			p.setDirezione(prenotazione.getDirezione());
			//if(prenotazione.getGiorno()!=null)
			p.setGiorno(data);
			if(prenotazione.getNomeBambino()!=null)
			p.setNomeBambino(prenotazione.getNomeBambino());
			if(prenotazione.getNomeFermataDiArrivo()!=null)
			p.setNomeFermataDiArrivo(prenotazione.getNomeFermataDiArrivo());
			if(prenotazione.getNomeFermataDiPartenza()!=null)
			p.setNomeFermataDiPartenza(prenotazione.getNomeFermataDiPartenza());
			//if(prenotazione.getNomeLinea()!=null)
			p.setNomeLinea(nomeLinea);
			if(prenotazione.getOra()!=null)
			p.setOra(prenotazione.getOra());
			if(prenotazione.getNomePrenotatore()!=null)
			p.setNomePrenotatore(prenotazione.getNomePrenotatore());
		}
		return p;
	}

	@Override
	public void deletePrenotazione(String nomeLinea, String data, Long prenotazioneId) {
		prenotazioneRepository.deleteByNomeLineaIgnoreCaseAndGiornoAndId(nomeLinea, data, prenotazioneId);
	}

	@Override
	public Prenotazione getPrenotazione(String nomeLinea, String data, Long prenotazioneId) {
		
		return prenotazioneRepository.findByNomeLineaIgnoreCaseAndGiornoAndId(nomeLinea, data, prenotazioneId);
	}

}
