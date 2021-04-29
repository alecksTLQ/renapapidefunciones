package com.example.springsocial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsocial.model.TbitacoraModel;


@Service
public class BitacoraServiceImp implements BitacoraService{
	
	@Autowired
	private TbitcoraRepository rpBitacora;
	
	@Override
	@Transactional 
	public void guardar(TbitacoraModel model) {
		rpBitacora.save(model);
	}
}
