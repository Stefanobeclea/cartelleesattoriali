package it.prova.cartelleesattoriali.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.cartelleesattoriali.dto.ContribuenteDTO;
import it.prova.cartelleesattoriali.service.ContribuenteService;

@RestController
@RequestMapping("api/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteDTOService;

	@GetMapping
	public List<ContribuenteDTO> getAll() {
		return ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteDTOService.listAllElementsEager(), true);
	}
}
