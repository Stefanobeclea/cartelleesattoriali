package it.prova.cartelleesattoriali.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartelleesattoriali.model.CartellaEsattoriale;
import it.prova.cartelleesattoriali.model.Contribuente;
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
	private ContribuenteDTO contribuenteDTO;

	public CartellaEsattorialeDTO() {
		// TODO Auto-generated constructor stub
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
		return contribuenteDTO;
	}

	public void setContribuenteDTO(ContribuenteDTO contribuenteDTO) {
		this.contribuenteDTO = contribuenteDTO;
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
		this.contribuenteDTO = contribuenteDTO;
	}

	public CartellaEsattorialeDTO(Long id, @NotBlank(message = "{descrizione.notblank}") String descrizione,
			@NotNull(message = "{importo.notblank}") Integer importo,
			@NotNull(message = "{stato.notblank}") Stato stato) {
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
		this.contribuenteDTO = contribuenteDTO;
	}
	
	public CartellaEsattoriale buildCartellaEsattorialeModel() {
		Contribuente thisContribuente = null;
		if( this.contribuenteDTO != null)
			thisContribuente = this.contribuenteDTO.buildContribuenteModel();
		return new CartellaEsattoriale(this.id, this.descrizione, this.importo, this.stato,
				thisContribuente);
	}

	public static CartellaEsattorialeDTO buildCartellaEsattorialeDTOFromModel(CartellaEsattoriale cartellaEsattorialeModel, boolean includeContribuente) {
		CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(cartellaEsattorialeModel.getId(), cartellaEsattorialeModel.getDescrizione(), cartellaEsattorialeModel.getImporto(),
				cartellaEsattorialeModel.getStato());

		if (includeContribuente)
			result.setContribuenteDTO(ContribuenteDTO.buildContribuenteDTOFromModel(cartellaEsattorialeModel.getContribuente(), false));

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
