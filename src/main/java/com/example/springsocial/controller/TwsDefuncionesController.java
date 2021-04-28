package com.example.springsocial.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.*;
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
import com.example.springsocial.process.Defunciones;
import com.example.springsocial.repository.ElementRepository;
import com.example.springsocial.repository.EntitiRepository;
import com.example.springsocial.repository.TwsdefuncionesRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.ExtraerDatos;
import com.example.springsocial.tools.MetodoEncriptacion;
import com.example.springsocial.tools.RestResponse;

import net.bytebuddy.asm.Advice.This;

@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("defunciones")
public class TwsDefuncionesController implements CrudController {
	
	@Autowired 
	private TwsdefuncionesRepository rpDefunciones; //repositorio Defunciones
	
	@Autowired 
	private ElementRepository elementRepository;
	private EntitiRepository entitiRepository;
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private String moduloName = "TwsdefuncionesModel";
	
	private Defunciones defunciones = new Defunciones(); //objeto del model defunciones
	
	private CRUD crud = new CRUD();
	
    @PostConstruct
    private void init() {
    	crud.setRepository(this.rpDefunciones);
    	crud.setModelName(this.moduloName);
    	crud.setEntitiRepository(this.entitiRepository);
    	crud.setElementRepository(this.elementRepository);
    	repositories.put(this.moduloName, this.rpDefunciones);
    }
	
    @Override
	@PostMapping("create") //INSERCION A TABLA T_WS_DEFUNCIONES
	public RestResponse create(@CurrentUser UserPrincipal userPrincipal, HttpServletRequest request, @RequestBody Object createElement) {
		
		String AuthTokenHeader = request.getHeader("Authorization");
		RestResponse response = new RestResponse();
		ObjectSetGet data = new ObjectSetGet();
		
		try {
			
			data.setObject(createElement);
			if(data.getValue("ENTREGA")==null || data.getValue("ENTREGA")=="") return new RestResponse(null, new CustomException("completar el campo entrega", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("TIPO_ENTREGA")==null || data.getValue("TIPO_ENTREGA")=="") return new RestResponse(null, new CustomException("completar el campo entrega", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("PRIMER_NOMBRE")==null || data.getValue("PRIMER_NOMBRE")=="") return new RestResponse(null, new CustomException("completar el campo entrega", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			if(data.getValue("PRIMER_APELLIDO")==null || data.getValue("PRIMER_APELLIDO")=="") return new RestResponse(null, new CustomException("completar el campo entrega", ErrorCode.REST_CREATE, this.getClass().getSimpleName(),0));
			
			
			defunciones.setEntityManagerFactory(entityManagerFactory);
			defunciones.setUserPrincipal(userPrincipal);
			defunciones.SetData(createElement);
			defunciones.Save();
			
			if(defunciones.GetResponse().getError()!=null) throw new Exception(defunciones.GetResponse().getError().toString());
			else {
				response.setData("datos insertados"+defunciones.GetResponse().getData());
			}
		}catch(Exception e) {
			response.setError(e);
		}

		return response;
	}
    
    
    
    /* METODO DE PROCESAMIENTO  */
	@GetMapping("archivo")
	@SuppressWarnings("unused")
	public RestResponse Proceso() {
		MetodoEncriptacion decriptador = new MetodoEncriptacion();
		RestResponse response = new RestResponse();
		JSONObject objeto = new JSONObject();
		ExtraerDatos datos = new ExtraerDatos();
		JSONArray array = null;
		String key, usuario, pw, entidad, error="", ESTATUS="";
		Date fecha = new Date();
		Integer coderror = 0, Cantidad_Reg_Linea=0;
		
			
		try {
			
			try {
				
				key = datos.leerArchivo("clave");
				usuario = decriptador.desencriptar(datos.leerArchivo("usuario"), key);
				pw = decriptador.desencriptar(datos.leerArchivo("password"), key);
				entidad = datos.leerArchivo("entidad");
				
				/*START RECUPERAR DATOS DEL WS*/
				JSONParser parser = new JSONParser();
				
				Object datojson = parser.parse(new FileReader("C:\\Users\\Desarrollo06\\Documents\\apuntes\\archio.json"));
				JSONObject contenedor = (JSONObject) datojson;
				//almacena todo el arreglo de datos
				array = (JSONArray) contenedor.get("datos");
				
				Cantidad_Reg_Linea = array.size();
				
				response.setData(array);
				//obtiene fila x fial del arreglo de datos
				//JSONObject fila = (JSONObject) array.get(0);
				
				
				/*END RECUPERAR DATOS DEL WS*/
				
				
				
			}catch(Exception e) {
				ESTATUS = "0";
				System.out.print(error);
				System.out.println(e.getMessage());
			}
			
			
			if(Cantidad_Reg_Linea>0) {
				ESTATUS="1";
				
				
				InsercionDefunciones();
				
				/*START PREPARAR BACKUP*/
				JSONParser parser = new JSONParser();
				try {
					/*Object obj = parser.parse(new FileReader("C:\\Users\\Desarrollo06\\Documents\\apuntes\\archio.json"));
					JSONObject json = (JSONObject) obj;
					System.out.println("JSON LEIDO: "+	json);
					
					JSONArray array = (JSONArray) json.get("datos");
					response.setData(array);*/
					
					FileWriter fichero = new FileWriter("C:\\Users\\Desarrollo06\\Documents\\apuntes\\fichero.txt");
					
					for(int x =0; x<array.size();x++) {
						JSONObject json1 = (JSONObject) array.get(x);
						fichero.write(" "+json1.get("ENTREGA") +"|"+json1.get("TIPO_ENTREGA")+"|"+json1.get("FECHA_DEFUNCION")+" \n");
					}
					
					fichero.close();
					
				}catch(FileNotFoundException e) {
					System.out.print(e);
				}catch(IOException e) {
					System.out.print(e);
				}
				
				/*START INSERT BITACORA*/
				
				TbitacoraController bitacora = new TbitacoraController();
				TbitacoraModel mdlBitacora = new TbitacoraModel();
				bitacora.create(null, null, mdlBitacora);
				
				/*END INSERT BITACORA*/
			}
			
			
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		
		return response;
		
	}
	
	
	public void InsercionDefunciones() {
		
	}
    
    
}
