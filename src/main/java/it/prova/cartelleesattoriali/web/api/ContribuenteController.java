package it.prova.cartelleesattoriali.web.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import it.prova.cartelleesattoriali.dto.CartellaEsattorialeDTO;
import it.prova.cartelleesattoriali.dto.ContribuenteDTO;
import it.prova.cartelleesattoriali.dto.ContribuenteReportDTO;
import it.prova.cartelleesattoriali.model.CartellaEsattoriale;
import it.prova.cartelleesattoriali.model.Contribuente;
import it.prova.cartelleesattoriali.model.Stato;
import it.prova.cartelleesattoriali.service.ContribuenteService;
import it.prova.cartelleesattoriali.web.api.exception.ContribuenteConCartelleException;
import it.prova.cartelleesattoriali.web.api.exception.ContribuenteNotFoundException;
import it.prova.cartelleesattoriali.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteService;

	
	@GetMapping
	public List<ContribuenteDTO> getAll() {
		return ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.listAllElementsEager(), true);
	} 
	
	@GetMapping("/{id}")
	public ContribuenteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoConCartelle(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuente, true);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContribuenteDTO createNew(@Valid @RequestBody ContribuenteDTO contribuenteInput) {
		if(contribuenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non ?? ammesso fornire un id per la creazione");
		
		Contribuente contribuenteInserito = contribuenteService.inserisciNuovo(contribuenteInput.buildContribuenteModel());
		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteInserito, false);
	}
	
	@PutMapping("/{id}")
	public ContribuenteDTO update(@Valid @RequestBody ContribuenteDTO contribuenteInput, @PathVariable(required = true) Long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElemento(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		contribuenteInput.setId(id);
		Contribuente contribuenteAggiornato = contribuenteService.aggiorna(contribuenteInput.buildContribuenteModel());
		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteAggiornato, false);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoConCartelle(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);
		if(!contribuente.getCartelle().isEmpty())
			throw new ContribuenteConCartelleException("Contribuente Possiede Cartelle");

		contribuenteService.rimuovi(contribuente);
	}
	
	@PostMapping("/search")
	public List<ContribuenteDTO> search(@RequestBody ContribuenteDTO example) {
		return ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.findByExample(example.buildContribuenteModel()),
				false);
	}
	
	@GetMapping("/report")
	public List<ContribuenteReportDTO> reportContribuenti() {
		List<ContribuenteReportDTO> contribuentiPerReport = ContribuenteReportDTO.createContribuenteReportDTOListFromModelList(contribuenteService.listAllElementsEager(), true);
		
		for (ContribuenteReportDTO contribuenteItem : contribuentiPerReport) {
			int importoCartelle = 0;
			int contenzioso = 0;
			int conclusoEPagato = 0;
			for (CartellaEsattorialeDTO cartellaItem : contribuenteItem.getCartelle()) {
				importoCartelle += cartellaItem.getImporto();
				if(cartellaItem.getStato().equals(Stato.CONCLUSA))
					conclusoEPagato += cartellaItem.getImporto();
				if(cartellaItem.getStato().equals(Stato.IN_CONTENZIOSO))
					contenzioso += cartellaItem.getImporto();
			}
			contribuenteItem.setContenzioso(contenzioso);
			contribuenteItem.setPagato(conclusoEPagato);
			contribuenteItem.setImporto(importoCartelle);
		}
		return contribuentiPerReport;
	}
	
	@GetMapping("/verifica")
	public List<ContribuenteReportDTO> verificaContenziosi(){
		List<Contribuente> contribuentiPerVerifica = contribuenteService.listAllElementsEager();
		List<ContribuenteReportDTO> listaRisultato = new ArrayList<ContribuenteReportDTO>();
		
		for (Contribuente contribuenteItem : contribuentiPerVerifica) {
			ContribuenteReportDTO contribuenteReport = ContribuenteReportDTO.buildContribuenteReportDTOFromModel(contribuenteItem, false);
			for (CartellaEsattoriale cartellaItem : contribuenteItem.getCartelle()) {
				if(cartellaItem.getStato().equals(Stato.IN_CONTENZIOSO)) {
					contribuenteReport.setDaAttenzionare(true);
				}
			}
			listaRisultato.add(contribuenteReport);
		}
		return listaRisultato;
	}
}
