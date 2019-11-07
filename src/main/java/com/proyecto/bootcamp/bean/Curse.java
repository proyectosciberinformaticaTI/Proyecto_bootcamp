package com.proyecto.bootcamp.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "IMFORMATION CURSES")
@Document(collection = "Curse")
public class Curse {

	@ApiModelProperty(notes = "Id is important", dataType = "String", example = "5db06ecebf64e2be068459b5")
	@Id
	private String _id;

	@ApiModelProperty(notes = "nombre is important", dataType = "String", example = "matematicas")
	@NotNull(message = "name is important")
	private String nombre;

	@ApiModelProperty(notes = "Estado is important", dataType = "String", example = "activo")
	@NotNull(message = "estado is important")
	private String estado;

	
	@ApiModelProperty(notes = "Minimo is important", dataType = "String", example = "23")
	@NotNull(message = "minimo is important")
	private String minimo;

	
	@ApiModelProperty(notes = "Maximo is important", dataType = "String", example = "24")
	@NotNull(message = "Maximo is important")
	private String maximo;


	
	
	@ApiModelProperty(notes = "Profesor is important", dataType = "String", example = "Javier")
	private String profesor;

    

	
	
	
	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}


	public String getMinimo() {
		return minimo;
	}

	public void setMinimo(String minimo) {
		this.minimo = minimo;
	}

	public String getMaximo() {
		return maximo;
	}

	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}

	public Curse() {

	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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



}
