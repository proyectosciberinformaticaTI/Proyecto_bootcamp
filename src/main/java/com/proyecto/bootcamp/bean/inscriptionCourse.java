package com.proyecto.bootcamp.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "INSCRIPTION COURSE")
@Document(collection = "inscriptionCourse")
public class inscriptionCourse {

	@ApiModelProperty(notes = "Id is important", dataType = "String", example = "5db06ecebf64e2be068459b5")
	@Id
	private String _id;

	@ApiModelProperty(notes = "nombre is important", dataType = "String", example = "matematicas")
	@NotNull(message = "name is important")
	private String nombre;

	@ApiModelProperty(notes = "Estado is important", dataType = "String", example = "activo")
	@NotNull(message = "estado is important")
	private String estado;

	@ApiModelProperty(notes = "Profesor is important", dataType = "String", example = "Javier")
	@NotNull(message = "course is important")
	private String course;




	public inscriptionCourse() {
		
	}

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}







	
	
	
	
	
	



}