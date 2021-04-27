package com.example.springsocial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBITACORA_WS_DPI")
public class TbitacoraModel {
	
	@Column(name="ESTATUS")
	private String ESTATUS;
	@Column(name="ENTREGA")
	private Date ENTREGA;
	@Id
	@Column(name="TIPOENTREGA")
	private Long tipoentrega;
	@Column(name="FECHASEMISIONES")
	private Date FECHASEMISIONES; 
	@Column(name="FECHAINCONSUMO")
	private Date FECHAINCONSUMO;  
	@Column(name="FECHAFINCONSUMO")
	private Date FECHAFINCONSUMO;
	@Column(name="TOTALREGCONSUMO")
	private Integer TOTALREGCONSUMO;
	@Column(name="FECININSERDATA")
	private Date FECININSERDATA;
	@Column(name="FECFININSERDATA")
	private Date FECFININSERDATA;
	@Column(name="TOTALREGINSERDATA")
	private Integer TOTALREGINSERDATA;
	@Column(name="FECININSERTIMG")
	private Date FECININSERTIMG;
	@Column(name="FECFININSERTIMG")
	private Date FECFININSERTIMG;
	@Column(name="ACTUALIZADOS")
	private Integer ACTUALIZADOS;
	@Column(name="NUEVOS")
	private Integer NUEVOS;
	@Column(name="FECINBACKDATA")
	private Date FECINBACKDATA;
	@Column(name="FECFINBACKDATA")
	private Date FECFINBACKDATA;
	@Column(name="TOTALREGBACKDATA")
	private Integer TOTALREGBACKDATA;
	@Column(name="FECINBACKIMG")
	private Date FECINBACKIMG;
	@Column(name="FECFINBACKIMG")
	private Date FECFINBACKIMG;
	@Column(name="TOTALREGBACKIMG")
	private Integer TOTALREGBACKIMG;
	
	
	public String getESTATUS() {
		return ESTATUS;
	}
	public void setESTATUS(String eSTATUS) {
		ESTATUS = eSTATUS;
	}
	public Date getENTREGA() {
		return ENTREGA;
	}
	public void setENTREGA(Date eNTREGA) {
		ENTREGA = eNTREGA;
	}
	
	public Long getTipoentrega() {
		return tipoentrega;
	}
	public void setTipoentrega(Long tipoentrega) {
		this.tipoentrega = tipoentrega;
	}
	public Date getFECHASEMISIONES() {
		return FECHASEMISIONES;
	}
	public void setFECHASEMISIONES(Date fECHASEMISIONES) {
		FECHASEMISIONES = fECHASEMISIONES;
	}
	public Date getFECHAINCONSUMO() {
		return FECHAINCONSUMO;
	}
	public void setFECHAINCONSUMO(Date fECHAINCONSUMO) {
		FECHAINCONSUMO = fECHAINCONSUMO;
	}
	public Date getFECHAFINCONSUMO() {
		return FECHAFINCONSUMO;
	}
	public void setFECHAFINCONSUMO(Date fECHAFINCONSUMO) {
		FECHAFINCONSUMO = fECHAFINCONSUMO;
	}
	public Integer getTOTALREGCONSUMO() {
		return TOTALREGCONSUMO;
	}
	public void setTOTALREGCONSUMO(Integer tOTALREGCONSUMO) {
		TOTALREGCONSUMO = tOTALREGCONSUMO;
	}
	public Date getFECININSERDATA() {
		return FECININSERDATA;
	}
	public void setFECININSERDATA(Date fECININSERDATA) {
		FECININSERDATA = fECININSERDATA;
	}
	public Date getFECFININSERDATA() {
		return FECFININSERDATA;
	}
	public void setFECFININSERDATA(Date fECFININSERDATA) {
		FECFININSERDATA = fECFININSERDATA;
	}
	public Integer getTOTALREGINSERDATA() {
		return TOTALREGINSERDATA;
	}
	public void setTOTALREGINSERDATA(Integer tOTALREGINSERDATA) {
		TOTALREGINSERDATA = tOTALREGINSERDATA;
	}
	public Date getFECININSERTIMG() {
		return FECININSERTIMG;
	}
	public void setFECININSERTIMG(Date fECININSERTIMG) {
		FECININSERTIMG = fECININSERTIMG;
	}
	public Date getFECFININSERTIMG() {
		return FECFININSERTIMG;
	}
	public void setFECFININSERTIMG(Date fECFININSERTIMG) {
		FECFININSERTIMG = fECFININSERTIMG;
	}
	public Integer getACTUALIZADOS() {
		return ACTUALIZADOS;
	}
	public void setACTUALIZADOS(Integer aCTUALIZADOS) {
		ACTUALIZADOS = aCTUALIZADOS;
	}
	public Integer getNUEVOS() {
		return NUEVOS;
	}
	public void setNUEVOS(Integer nUEVOS) {
		NUEVOS = nUEVOS;
	}
	public Date getFECINBACKDATA() {
		return FECINBACKDATA;
	}
	public void setFECINBACKDATA(Date fECINBACKDATA) {
		FECINBACKDATA = fECINBACKDATA;
	}
	public Date getFECFINBACKDATA() {
		return FECFINBACKDATA;
	}
	public void setFECFINBACKDATA(Date fECFINBACKDATA) {
		FECFINBACKDATA = fECFINBACKDATA;
	}
	public Integer getTOTALREGBACKDATA() {
		return TOTALREGBACKDATA;
	}
	public void setTOTALREGBACKDATA(Integer tOTALREGBACKDATA) {
		TOTALREGBACKDATA = tOTALREGBACKDATA;
	}
	public Date getFECINBACKIMG() {
		return FECINBACKIMG;
	}
	public void setFECINBACKIMG(Date fECINBACKIMG) {
		FECINBACKIMG = fECINBACKIMG;
	}
	public Date getFECFINBACKIMG() {
		return FECFINBACKIMG;
	}
	public void setFECFINBACKIMG(Date fECFINBACKIMG) {
		FECFINBACKIMG = fECFINBACKIMG;
	}
	public Integer getTOTALREGBACKIMG() {
		return TOTALREGBACKIMG;
	}
	public void setTOTALREGBACKIMG(Integer tOTALREGBACKIMG) {
		TOTALREGBACKIMG = tOTALREGBACKIMG;
	}
	
}
