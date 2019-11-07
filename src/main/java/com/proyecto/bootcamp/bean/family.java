package com.proyecto.bootcamp.bean;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "family")
public class family {

	

	
	
	@Id
	private String _id;
	
	
	
	
	private String nombre;
	
	
	
	private List<Students>   student;



	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Students> getLista() {
		
		return student;
	}



	public void setLista(List<Students> student) {
		this.student = student;
	}
	
	

	
	
	
	
	
	
}
