package it.prova.cartelleesattoriali.repository.cartellaesattoriale;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartelleesattoriali.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository{
	@Query("from CartellaEsattoriale c join fetch c.contribuente where c.id = ?1")
	CartellaEsattoriale findSingleCartellaEsattorialeEager(Long id);
	
	@Query("select c from CartellaEsattoriale c join fetch c.contribuente")
	List<CartellaEsattoriale> findAllCartellaEsattorialeEager();
}
