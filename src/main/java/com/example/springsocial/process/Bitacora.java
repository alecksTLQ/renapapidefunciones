package com.example.springsocial.process;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.TbitacoraModel;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;

@SuppressWarnings({"rawtypes"})
public class Bitacora {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction = null;
	private EntityManager entityManager = null;
	private ModelSetGetTransaction modelTransaction = new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data = new ObjectSetGet();
	private TbitacoraModel bitacora = new TbitacoraModel();
	private UserPrincipal userPrincipal = null;
	private RestResponse response = new RestResponse();
	
	public void setUserPrincipal(UserPrincipal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		if(entityManagerFactory!=null) {
			this.entityManagerFactory = entityManagerFactory;
		}else {
			System.out.print("dentro del else");
		}
	}
	public void setData(Object createElement) {
		data.setObject(createElement);
	}
	
	private void startTransaction() {
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
		this.transaction.begin();
		this.modelTransaction.setEntityManager(entityManager);
	}
	
	public TbitacoraModel getData() {
		return this.bitacora;
	}
	
	private void insertBitacora()throws Exception{
		this.bitacora = new TbitacoraModel();
		this.bitacora.setESTATUS(data.getValue("ESTATUS").toString());
		this.bitacora.setENTREGA(data.getValue("ENTREGA").toString());
		this.bitacora.setTIPOENTREGA(Integer.parseInt(data.getValue("TIPOENTREGA").toString()));
		this.bitacora.setFECHASEMISIONES(data.getValue("FECHASEMISIONES").toString());
		this.bitacora.setFECHAINCONSUMO(data.getValue("FECHAINCONSUMO").toString());
		this.bitacora.setFECHAFINCONSUMO(data.getValue("FECHAFINCONSUMO").toString());
		
		modelTransaction.saveWithFlush(bitacora);
	}
	
	private void confirmTransactionAndSetResult() {
		transaction.commit();
		response.setData(this.bitacora);
	}
	
	public void save() throws Exception{
		try {
			
			startTransaction();
			insertBitacora();
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
