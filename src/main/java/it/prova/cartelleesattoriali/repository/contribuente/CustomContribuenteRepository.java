package it.prova.cartelleesattoriali.repository.contribuente;

import java.util.List;

import it.prova.cartelleesattoriali.model.Contribuente;

public interface CustomContribuenteRepository {
	List<Contribuente> findByExample(Contribuente example);
}
