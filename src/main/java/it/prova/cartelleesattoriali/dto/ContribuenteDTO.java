package it.prova.cartelleesattoriali.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartelleesattoriali.model.Contribuente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContribuenteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataNascita;

	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	@JsonIgnoreProperties(value = { "contribuente" })
	private Set<CartellaEsattorialeDTO> cartelle = new HashSet<CartellaEsattorialeDTO>(0);

	public ContribuenteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ContribuenteDTO(String nome, String cognome, Date dataNascita, String codiceFiscale, String indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteDTO(String nome, String cognome, Date dataNascita, String codiceFiscale, String indirizzo,
			Set<CartellaEsattorialeDTO> cartelle) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public ContribuenteDTO(Long id, String nome, String cognome, Date dataNascita, String codiceFiscale,
			String indirizzo, Set<CartellaEsattorialeDTO> cartelle) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public ContribuenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataNascita,
			@NotBlank(message = "{codiceFiscale.notblank}") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Set<CartellaEsattorialeDTO> getCartelle() {
		return cartelle;
	}

	public void setCartelle(Set<CartellaEsattorialeDTO> cartelle) {
		this.cartelle = cartelle;
	}
	
	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataNascita, this.codiceFiscale, this.indirizzo);
	}

	public static ContribuenteDTO buildContribuenteDTOFromModel(Contribuente contribuenteModel, boolean includeCartelle) {
		ContribuenteDTO result = new ContribuenteDTO(contribuenteModel.getId(), contribuenteModel.getNome(), contribuenteModel.getCognome(),
				 contribuenteModel.getDataNascita(), contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo());
		if(includeCartelle)
			result.setCartelle(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(contribuenteModel.getCartelle(), false));
		return result;
	}

	public static List<ContribuenteDTO> createContribuenteDTOListFromModelList(List<Contribuente> modelListInput, boolean includeCartelle) {
		return modelListInput.stream().map(contribuenteEntity -> {
			ContribuenteDTO result = ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteEntity,includeCartelle);
			if(includeCartelle)
				result.setCartelle(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(contribuenteEntity.getCartelle(), false));
			return result;
		}).collect(Collectors.toList());
	}
}
