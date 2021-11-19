package it.prova.cartelleesattoriali.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.cartelleesattoriali.model.Contribuente;
import it.prova.cartelleesattoriali.repository.contribuente.ContribuenteRepository;

public class ContribuenteServiceImpl implements ContribuenteService{

	@Autowired
	ContribuenteRepository repository;
	
	@Override
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>)repository.findAll();
	}

	@Override
	public List<Contribuente> listAllElementsEager() {
		return (List<Contribuente>)repository.findAllEager();
	}

	@Override
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Contribuente caricaSingoloElementoConCartelle(Long id) {
		return repository.findByIdEager(id);
	}

	@Override
	public Contribuente aggiorna(Contribuente contribuenteInstance) {
		return repository.save(contribuenteInstance);
	}

	@Override
	public Contribuente inserisciNuovo(Contribuente contribuenteInstance) {
		return repository.save(contribuenteInstance);
	}

	@Override
	public void rimuovi(Contribuente contribuenteInstance) {
		repository.delete(contribuenteInstance);
		
	}

	@Override
	public List<Contribuente> findByExample(Contribuente example) {
		return repository.findByExample(example);
	}

}
