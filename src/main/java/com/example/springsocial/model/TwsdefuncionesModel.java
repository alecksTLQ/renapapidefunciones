package com.example.springsocial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_WS_DEFUNCIONES")
public class TwsdefuncionesModel {
	
	@Column(name="ENTREGA")
	 private String ENTREGA;
	 @Id
	 @Column(name="TIPO_ENTREGA")
	 private Long TIPO_ENTREGA;
	 @Column(name="PRIMER_NOMBRE")
	 private String PRIMER_NOMBRE;
	 @Column(name="PRIMER_APELLIDO")
	 private String PRIMER_APELLIDO;

	 
	public TwsdefuncionesModel() {
		
	}
	
	public String getENTREGA() {
		return ENTREGA;
	}

	public void setENTREGA(String eNTREGA) {
		ENTREGA = eNTREGA;
	}

	public Long getTIPO_ENTREGA() {
		return TIPO_ENTREGA;
	}
	public void setTIPO_ENTREGA(Long tIPO_ENTREGA) {
		TIPO_ENTREGA = tIPO_ENTREGA;
	}
	

	public String getPRIMER_NOMBRE() {
		return PRIMER_NOMBRE;
	}
	public void setPRIMER_NOMBRE(String pRIMER_NOMBRE) {
		PRIMER_NOMBRE = pRIMER_NOMBRE;
	}
	
	

	public String getPRIMER_APELLIDO() {
		return PRIMER_APELLIDO;
	}
	public void setPRIMER_APELLIDO(String pRIMER_APELLIDO) {
		PRIMER_APELLIDO = pRIMER_APELLIDO;
	}

	public TwsdefuncionesModel(String eNTREGA, Long tIPO_ENTREGA, String pRIMER_NOMBRE, String pRIMER_APELLIDO) {
	
		ENTREGA = eNTREGA;
		TIPO_ENTREGA = tIPO_ENTREGA;
		PRIMER_NOMBRE = pRIMER_NOMBRE;
		PRIMER_APELLIDO = pRIMER_APELLIDO;
	}


	

}
