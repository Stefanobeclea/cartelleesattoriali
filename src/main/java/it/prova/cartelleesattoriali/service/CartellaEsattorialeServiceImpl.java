package it.prova.cartelleesattoriali.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cartelleesattoriali.model.CartellaEsattoriale;
import it.prova.cartelleesattoriali.repository.cartellaesattoriale.CartellaEsattorialeRepository;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService{

	@Autowired
	private CartellaEsattorialeRepository repository;
	
	@Override
	public List<CartellaEsattoriale> listAllElements(boolean eager) {
		if (eager)
			return (List<CartellaEsattoriale>) repository.findAllCartellaEsattorialeEager();

		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Override
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findSingleCartellaEsattorialeEager(id);
	}

	@Transactional
	@Override
	public CartellaEsattoriale aggiorna(CartellaEsattoriale cartellaEsattorialeInstance) {
		return repository.save(cartellaEsattorialeInstance);
	}

	@Transactional
	@Override
	public CartellaEsattoriale inserisciNuovo(CartellaEsattoriale cartellaEsattorialeInstance) {
		return repository.save(cartellaEsattorialeInstance);
	}

	@Transactional
	@Override
	public void rimuovi(CartellaEsattoriale cartellaEsattorialeInstance) {
		repository.delete(cartellaEsattorialeInstance);
		
	}

	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		return repository.findByExample(example);
	}

}
