package com.proyecto.bootcamp.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "IMFORMATION TEACHER")
@Document(collection = "Profesor")
public class Profesor {

	
	
	
	@ApiModelProperty(notes = "Id is important", dataType = "String", example = "5db06ecebf64e2be068459b5")
	@Id
	private String _id;
	
	
	
	
	@ApiModelProperty(notes = "name is important", dataType = "String", example = "Javier")
	@NotNull(message = "name is important")
	private  String nombre;
	
	
	
	
	
	
	@ApiModelProperty(notes = "dni is important", dataType = "String", example = "4674858")
	@NotNull(message = "dni is important")
	private String  dni;
	
	
	
	
	
	
	
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	
	
	
	
}
