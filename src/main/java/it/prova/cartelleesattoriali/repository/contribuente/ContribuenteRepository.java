package it.prova.cartelleesattoriali.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartelleesattoriali.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository{
	
	@Query("select distinct c from Contribuente c left join fetch c.cartelle ")
	List<Contribuente> findAllEager();

	@Query("from Contribuente c left join fetch c.cartelle where c.id=?1")
	Contribuente findByIdEager(Long idContribuente);
}
