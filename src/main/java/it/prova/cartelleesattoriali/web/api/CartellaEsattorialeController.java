package it.prova.cartelleesattoriali.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.cartelleesattoriali.dto.CartellaEsattorialeDTO;
import it.prova.cartelleesattoriali.model.CartellaEsattoriale;
import it.prova.cartelleesattoriali.service.CartellaEsattorialeService;
import it.prova.cartelleesattoriali.web.api.exception.CartellaEsattorialeNotFoundException;
import it.prova.cartelleesattoriali.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/cartellaesattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@GetMapping
	public List<CartellaEsattorialeDTO> getAll() {
		return CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartellaEsattorialeService.listAllElements(true), true) ;
	}
	
	@PostMapping
	public CartellaEsattorialeDTO createNew(@Valid @RequestBody CartellaEsattorialeDTO cartellaInput) {
		
		if(cartellaInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		System.out.println("Qui ci arrivo");
		CartellaEsattoriale cartellaInserita = cartellaEsattorialeService.inserisciNuovo(cartellaInput.buildCartellaEsattorialeModel());
		return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaInserita, true);
	}
	
	@GetMapping("/{id}")
	public CartellaEsattorialeDTO findById(@PathVariable(value = "id", required = true) long id) {
		CartellaEsattoriale cartellaEsattoriale = cartellaEsattorialeService.caricaSingoloElementoEager(id);

		if (cartellaEsattoriale == null)
			throw new CartellaEsattorialeNotFoundException("CartellaEsattoriale not found con id: " + id);
		
		return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattoriale, true);
	}
	
	@PutMapping("/{id}")
	public CartellaEsattorialeDTO update(@Valid @RequestBody CartellaEsattorialeDTO cartellaEsattorialeInput, @PathVariable(required = true) Long id) {
		CartellaEsattoriale cartellaEsattoriale = cartellaEsattorialeService.caricaSingoloElemento(id);

		if (cartellaEsattoriale == null)
			throw new CartellaEsattorialeNotFoundException("CartellaEsattoriale not found con id: " + id);

		cartellaEsattorialeInput.setId(id);
		CartellaEsattoriale cartellaEsattorialeAggiornato = cartellaEsattorialeService.aggiorna(cartellaEsattorialeInput.buildCartellaEsattorialeModel());
		return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeAggiornato, false);
	}
}
