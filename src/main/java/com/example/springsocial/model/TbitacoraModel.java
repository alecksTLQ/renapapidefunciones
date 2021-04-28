package com.example.springsocial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TBITACORA_WS_DPI")
public class TbitacoraModel {
	
	
	@Column(name="ESTATUS")
	@NotEmpty
	private String ESTATUS;
	@Column(name="ENTREGA")
	@NotEmpty
	private String ENTREGA;
	@Id
	@NotNull
	@Column(name="TIPOENTREGA")
	private Long tipoentrega;
	@NotEmpty
	@Column(name="FECHASEMISIONES")
	private String FECHASEMISIONES;
	@NotEmpty
	@Column(name="FECHAINCONSUMO")
	private String FECHAINCONSUMO;
	@NotEmpty
	@Column(name="FECHAFINCONSUMO")
	private String FECHAFINCONSUMO;
	
	
	/*@Column(name="TOTALREGCONSUMO")
	private Integer TOTALREGCONSUMO;
	@Column(name="FECININSERDATA")
	private String FECININSERDATA;
	@Column(name="FECFININSERDATA")
	private String FECFININSERDATA;
	@Column(name="TOTALREGINSERDATA")
	private Integer TOTALREGINSERDATA;
	@Column(name="FECININSERTIMG")
	private String FECININSERTIMG;
	@Column(name="FECFININSERTIMG")
	private String FECFININSERTIMG;
	@Column(name="ACTUALIZADOS")
	private Integer ACTUALIZADOS;
	@Column(name="NUEVOS")
	private Integer NUEVOS;
	@Column(name="FECINBACKDATA")
	private String FECINBACKDATA;
	@Column(name="FECFINBACKDATA")
	private String FECFINBACKDATA;
	@Column(name="TOTALREGBACKDATA")
	private Integer TOTALREGBACKDATA;
	@Column(name="FECINBACKIMG")
	private String FECINBACKIMG;
	@Column(name="FECFINBACKIMG")
	private String FECFINBACKIMG;
	@Column(name="TOTALREGBACKIMG")
	private Integer TOTALREGBACKIMG;*/
	
	
	
	
	public TbitacoraModel() {
		
	}
	
	public TbitacoraModel(@NotEmpty String eSTATUS, @NotEmpty String eNTREGA, @NotNull Long tipoentrega,
			@NotEmpty String fECHASEMISIONES, @NotEmpty String fECHAINCONSUMO, @NotEmpty String fECHAFINCONSUMO) {
		super();
		ESTATUS = eSTATUS;
		ENTREGA = eNTREGA;
		this.tipoentrega = tipoentrega;
		FECHASEMISIONES = fECHASEMISIONES;
		FECHAINCONSUMO = fECHAINCONSUMO;
		FECHAFINCONSUMO = fECHAFINCONSUMO;
	}

	public String getESTATUS() {
		return ESTATUS;
	}
	public void setESTATUS(String eSTATUS) {
		ESTATUS = eSTATUS;
	}
	public String getENTREGA() {
		return ENTREGA;
	}
	public void setENTREGA(String eNTREGA) {
		ENTREGA = eNTREGA;
	}
	
	public Long getTipoentrega() {
		return tipoentrega;
	}
	public void setTipoentrega(Long tipoentrega) {
		this.tipoentrega = tipoentrega;
	}
	public String getFECHASEMISIONES() {
		return FECHASEMISIONES;
	}
	public void setFECHASEMISIONES(String fECHASEMISIONES) {
		FECHASEMISIONES = fECHASEMISIONES;
	}
	public String getFECHAINCONSUMO() {
		return FECHAINCONSUMO;
	}
	public void setFECHAINCONSUMO(String fECHAINCONSUMO) {
		FECHAINCONSUMO = fECHAINCONSUMO;
	}
	public String getFECHAFINCONSUMO() {
		return FECHAFINCONSUMO;
	}
	public void setFECHAFINCONSUMO(String fECHAFINCONSUMO) {
		FECHAFINCONSUMO = fECHAFINCONSUMO;
	}

}
