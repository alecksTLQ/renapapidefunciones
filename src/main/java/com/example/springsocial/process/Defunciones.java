package com.example.springsocial.process;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.model.TwsdefuncionesModel;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
//import com.example.springsocial.tools.DateTools;
//import com.example.springsocial.tools.SearchCriteriaTools;

@SuppressWarnings({"rawtypes"})
public class Defunciones {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction = null;
	private EntityManager entityManager = null;
	private ModelSetGetTransaction modelTransaction = new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	//private SearchCriteriaTools searchCriteriaTools = new SearchCriteriaTools();
	private ObjectSetGet data = new ObjectSetGet();
	private TwsdefuncionesModel defunciones = new TwsdefuncionesModel();
	private UserPrincipal userPrincipal = null;
	private RestResponse response = new RestResponse();
	
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal = userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory; }
	public void SetData(Object createElement) {data.setObject(createElement);}
	public ObjectSetGet getData2() {return data;}
	
	
	private void startTransaction() {
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
		this.transaction.begin();
		this.modelTransaction.setEntityManager(entityManager);
		
	}	
	public TwsdefuncionesModel getData() {		
		return this.defunciones; 
	}
	
	private void insertDefunciones() throws Exception {
		
		String strDateFormat = null;
		Date fecha = null;
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		fecha = formato.parse(data.getValue("ENTREGA").toString());
		strDateFormat = formato.format(fecha);

		this.defunciones = new TwsdefuncionesModel();
		this.defunciones.setTIPO_ENTREGA(Long.parseLong(data.getValue("TIPO_ENTREGA").toString()));
		this.defunciones.setPRIMER_APELLIDO(data.getValue("PRIMER_APELLIDO").toString());
		this.defunciones.setPRIMER_NOMBRE(data.getValue("PRIMER_NOMBRE").toString());	
		this.defunciones.setENTREGA(strDateFormat);
		
		modelTransaction.saveWithFlush(defunciones);
	}
	
	private void confirmTransactionAndSetResult() {
		transaction.commit();
		response.setData(this.defunciones);
	}
	
	public void Save() throws Exception {
		try {
			
			startTransaction();
			insertDefunciones();
			confirmTransactionAndSetResult();
			
		}catch(Exception e) {
			transaction.rollback();
			response.setError(e.getMessage());
		}finally {
			if(entityManager.isOpen()) entityManager.close();
		}
	}
	
	public RestResponse GetResponse() {
		return this.response;
	}

}
