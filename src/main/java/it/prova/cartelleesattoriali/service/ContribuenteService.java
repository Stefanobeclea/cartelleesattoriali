package it.prova.cartelleesattoriali.service;

import java.util.List;

import it.prova.cartelleesattoriali.model.Contribuente;

public interface ContribuenteService {
	List<Contribuente> listAllElements();

	List<Contribuente> listAllElementsEager();

	Contribuente caricaSingoloElemento(Long id);

	Contribuente caricaSingoloElementoConCartelle(Long id);

	Contribuente aggiorna(Contribuente contribuenteInstance);

	Contribuente inserisciNuovo(Contribuente contribuenteInstance);

	void rimuovi(Contribuente contribuenteInstance);

	List<Contribuente> findByExample(Contribuente example);

}
