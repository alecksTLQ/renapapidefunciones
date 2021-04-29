package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springsocial.model.TbitacoraModel;

public interface TbitcoraRepository extends JpaRepository<TbitacoraModel,Integer>, CrudRepository<TbitacoraModel,Integer>{
	
	@Query(value = "SELECT MAX(FECHAINCONSUMO) AS FECHA FROM TBITACORA_WS_DPI WHERE tipoentrega = :id", nativeQuery=true)
	String findBytipoentrega(@Param("id") Integer id);
	
}
