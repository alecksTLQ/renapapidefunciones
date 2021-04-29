package com.example.springsocial.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.example.springsocial.repository.BitacoraServiceImp;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.TbitcoraRepository;
import com.example.springsocial.repository.TwsdefuncionesRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.crud.CRUD;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.error.ErrorCode;
import com.example.springsocial.generic.CrudController;
import com.example.springsocial.model.TbitacoraModel;
import com.example.springsocial.process.Bitacora;
import com.example.springsocial.process.Defunciones;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.ExtraerDatos;
import com.example.springsocial.tools.MetodoEncriptacion;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes", "unchecked"})
//@RestController
//@RequestMapping("Bitacora")
public class TbitacoraController implements CrudController {
	
	@Autowired
	private TbitcoraRepository rpBitacora; //repositorio Bitacora
	private BitacoraServiceImp srvBitacora;
	//private TwsdefuncionesRepository rpDefunciones; //repositorio Defunciones
	
	@Autowired
	private ElementRepository elementRepository;
	private EntitiRepository entitiRepository;
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private String moduloName = "TbitacoraModel";
	
	private Bitacora bitacora = new Bitacora();
	
	private CRUD crud = new CRUD();
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		if(entityManagerFactory!=null) {
			this.entityManagerFactory = entityManagerFactory;
		}else {
			System.out.print("dentro del else");
		}
	}
	
	@PostConstruct
	private void init() {
		crud.setRepository(this.rpBitacora);
		crud.setModelName(this.moduloName);
		crud.setEntitiRepository(this.entitiRepository);
		crud.setElementRepository(this.elementRepository);
		repositories.put(this.moduloName, this.rpBitacora);
	}	
	
	/*OBTIENE FECHA MAXIMA DE TBITACORA*/
	//@GetMapping("FechaMax/{id}")
	public RestResponse list(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request,Integer id) {
		
		RestResponse response = new RestResponse();
		
		try {
			
		   String mdlBitacora =  rpBitacora.findBytipoentrega(id);
		   JSONObject objeto=new JSONObject();
		   objeto.put("fechaMaxima", mdlBitacora);
		   response.setData(objeto);
		   
			
		}catch(Exception exception) {
			CustomException customExcepction=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			response.setError(exception); 
			response.setError(customExcepction);
		}
		
		return response;
	}
	
	//@PostMapping("createBitacora")
	public RestResponse createBitaroca(Object createElement) {
		init();
		//String AuthTokenHeader = request.getHeader("Authorization");
		RestResponse response = new RestResponse();
		ObjectSetGet data = new ObjectSetGet();
		
		try {
			
			data.setObject(createElement);
			if(data.getValue("ESTATUS")==null || data.getValue("ESTATUS")=="") return new RestResponse(null, new CustomException("el estatus no puede esta vacio", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("ENTREGA")==null || data.getValue("ESTATUS")=="") return new RestResponse(null, new CustomException("el estatus no puede esta vacio", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("TIPOENTREGA")==null) return new RestResponse(null, new CustomException("el estatus no puede esta vacio", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("FECHASEMISIONES")==null || data.getValue("FECHASEMISIONES")=="") return new RestResponse(null, new CustomException("el estatus no puede esta vacio", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("FECHAINCONSUMO")==null || data.getValue("FECHAINCONSUMO")=="") return new RestResponse(null, new CustomException("el estatus no puede esta vacio", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("FECHAFINCONSUMO")==null || data.getValue("FECHAFINCONSUMO")=="") return new RestResponse(null, new CustomException("el estatus no puede esta vacio", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			
			bitacora.setEntityManagerFactory(entityManagerFactory);
			bitacora.setData(createElement);
			bitacora.save();
			
			if(bitacora.GetResponse().getError()!=null) throw new Exception(bitacora.GetResponse().getError().toString());
			else {
				response.setData("datos insertados bitacora "+bitacora.GetResponse().getData());
			}
			
		}catch(Exception e) {
			response.setError(e);
		}
		
		
		return response;
	}
	
	public String crearBitacora(TbitacoraModel model) {
		
		try {
			srvBitacora.guardar(model);
		}catch(Exception e) {
			System.out.print(e);
		}
		
		return "";
	}
	
	
}
