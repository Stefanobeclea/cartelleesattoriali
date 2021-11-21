package it.prova.cartelleesattoriali.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartelleesattoriali.model.Contribuente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContribuenteReportDTO {
	private Long id;

	private String nome;

	private String cognome;

	private Date dataNascita;

	private String codiceFiscale;

	private String indirizzo;

	@JsonIgnoreProperties(value = { "contribuente" })
	private Set<CartellaEsattorialeDTO> cartelle = new HashSet<CartellaEsattorialeDTO>(0); 
	
	//PER IL SECONDO ENDPOINT REPORT CONTRIBUENTI
	private Integer pagato;
	
	private Integer importo;
	
	private Integer contenzioso;
		
	//PER IL PRIMO ENDPOING VERIFICA CONTENZIOSI
	private Boolean daAttenzionare;

	public ContribuenteReportDTO() {
		super();
	}

	public ContribuenteReportDTO(Long id) {
		super();
		this.id = id;
	}

	public ContribuenteReportDTO(String nome, String cognome, Date dataNascita, String codiceFiscale,
			String indirizzo, Set<CartellaEsattorialeDTO> cartelle) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public ContribuenteReportDTO(Long id, String nome, String cognome, Date dataNascita, String codiceFiscale,
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

	public ContribuenteReportDTO(Long id, String nome, String cognome, Date dataNascita, String codiceFiscale,
			String indirizzo) {
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

	public Integer getPagato() {
		return pagato;
	}

	public void setPagato(Integer pagato) {
		this.pagato = pagato;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public Integer getContenzioso() {
		return contenzioso;
	}

	public void setContenzioso(Integer contenzioso) {
		this.contenzioso = contenzioso;
	}

	public Set<CartellaEsattorialeDTO> getCartelle() {
		return cartelle;
	}

	public void setCartelle(Set<CartellaEsattorialeDTO> cartelle) {
		this.cartelle = cartelle;
	}

	public Boolean getDaAttenzionare() {
		return daAttenzionare;
	}

	public void setDaAttenzionare(Boolean daAttenzionare) {
		this.daAttenzionare = daAttenzionare;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataNascita, this.codiceFiscale, this.indirizzo);
	}

	public static ContribuenteReportDTO buildContribuenteReportDTOFromModel(Contribuente contribuenteModel,
			boolean includeCartelle) {
		ContribuenteReportDTO result = new ContribuenteReportDTO(contribuenteModel.getId(),
				contribuenteModel.getNome(), contribuenteModel.getCognome(), contribuenteModel.getDataNascita(),
				contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo());
		if (includeCartelle)
			result.setCartelle(CartellaEsattorialeDTO
					.createCartellaEsattorialeDTOSetFromModelSet(contribuenteModel.getCartelle(), false));
		return result;
	}

	public static List<ContribuenteReportDTO> createContribuenteReportDTOListFromModelList(
			List<Contribuente> modelListInput, boolean includeCartelle) {
		return modelListInput.stream().map(contribuenteEntity -> {
			ContribuenteReportDTO result = ContribuenteReportDTO
					.buildContribuenteReportDTOFromModel(contribuenteEntity, includeCartelle);
			if (includeCartelle)
				result.setCartelle(CartellaEsattorialeDTO
						.createCartellaEsattorialeDTOSetFromModelSet(contribuenteEntity.getCartelle(), false));
			return result;
		}).collect(Collectors.toList());
	}


}
