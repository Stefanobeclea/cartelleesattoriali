package it.prova.cartelleesattoriali.repository.cartellaesattoriale;

import java.util.List;

import it.prova.cartelleesattoriali.model.CartellaEsattoriale;

public interface CustomCartellaEsattorialeRepository {
	List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);
}
