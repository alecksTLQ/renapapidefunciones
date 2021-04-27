package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.springsocial.model.TwsdefuncionesModel;

public interface TwsdefuncionesRepository extends JpaRepository<TwsdefuncionesModel, Integer>, 
CrudRepository<TwsdefuncionesModel, Integer>{
	
}
