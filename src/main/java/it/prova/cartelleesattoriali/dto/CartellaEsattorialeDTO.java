package it.prova.cartelleesattoriali.dto;

import java.util.List; 
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartelleesattoriali.model.CartellaEsattoriale;
import it.prova.cartelleesattoriali.model.Stato;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartellaEsattorialeDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotNull(message = "{importo.notblank}")
	private Integer importo;

	@NotNull(message = "{stato.notblank}")
	private Stato stato;

	@JsonIgnoreProperties(value = { "cartelle" })
	@NotNull(message = "{contribuente.notnull}")
	private ContribuenteDTO contribuente;

	public CartellaEsattorialeDTO() {
		// TODO Auto-generated constructor stub
	}

	public ContribuenteDTO getContribuente() {
		return contribuente;
	}

	public void setContribuente(ContribuenteDTO contribuente) {
		this.contribuente = contribuente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public ContribuenteDTO getContribuenteDTO() {
		return contribuente;
	}

	public void setContribuenteDTO(ContribuenteDTO contribuenteDTO) {
		this.contribuente = contribuenteDTO;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo, Stato stato) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo, Stato stato, ContribuenteDTO contribuenteDTO) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuenteDTO;
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, Stato stato) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, Stato stato,
			ContribuenteDTO contribuenteDTO) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuenteDTO;
	}
	
	public CartellaEsattoriale buildCartellaEsattorialeModel() {
		if (this.contribuente == null)
			return new CartellaEsattoriale(this.id, this.descrizione, this.importo, this.stato, null);
		else
			return new CartellaEsattoriale(this.id, this.descrizione, this.importo, this.stato,
					this.contribuente.buildContribuenteModel());
	}

	public static CartellaEsattorialeDTO buildCartellaEsattorialeDTOFromModel(
			CartellaEsattoriale cartellaEsattorialeModel, boolean includeContribuenti) {

		CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(cartellaEsattorialeModel.getId(),
				cartellaEsattorialeModel.getDescrizione(), cartellaEsattorialeModel.getImporto(),
				cartellaEsattorialeModel.getStato());

		if (includeContribuenti)
			result.setContribuente(
					ContribuenteDTO.buildContribuenteDTOFromModel(cartellaEsattorialeModel.getContribuente(), false));

		return result;
	}

	public static List<CartellaEsattorialeDTO> createCartellaEsattorialeDTOListFromModelList(List<CartellaEsattoriale> modelListInput, boolean includeContribuenti) {
		return modelListInput.stream().map(cartellaEsattorialeEntity -> {
			return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeEntity, includeContribuenti);
		}).collect(Collectors.toList());
	}

	public static Set<CartellaEsattorialeDTO> createCartellaEsattorialeDTOSetFromModelSet(Set<CartellaEsattoriale> modelListInput, boolean includeContribuenti) {
		return modelListInput.stream().map(cartellaEsattorialeEntity -> {
			return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeEntity, includeContribuenti);
		}).collect(Collectors.toSet());
	}
}
