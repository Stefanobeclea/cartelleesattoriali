package it.prova.cartelleesattoriali.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.cartelleesattoriali.dto.CartellaEsattorialeDTO;
import it.prova.cartelleesattoriali.service.CartellaEsattorialeService;

@RestController
@RequestMapping("api/cartellaesattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@GetMapping
	public List<CartellaEsattorialeDTO> getAll() {
		return CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartellaEsattorialeService.listAllElements(true), true) ;
	}
}
