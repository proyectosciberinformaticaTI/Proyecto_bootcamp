package com.proyecto.bootcamp.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


@ToString
@ApiModel(description = "IMFORMATION PARENTS")
@Document(collection = "Parents")
public class Parents {

	@ApiModelProperty(notes = "Id is important", dataType = "String", example = "5db06ecebf64e2be068459b5")
	@Id
	private String _id;

	@ApiModelProperty(notes = "Complement Name is important", dataType = "String", example = "Javier Paredes")
	@NotNull(message = "Complement Name is important")
	private String complementName;

	@ApiModelProperty(notes = "Nombres debe tener minimo 3 caracteres", dataType = "String", example = "masculino")
	@NotNull(message = "It is important")
	private String sex;

	@ApiModelProperty(notes = "Date Birth is important", dataType = "String", example = "2019-08-03")
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private Date dateBirth;

	@ApiModelProperty(notes = "Type identify is important", dataType = "String", example = "DNI")
	@NotNull(message = "Type of identity is important")
	private String typeidentify;

	@ApiModelProperty(notes = "Number identify", dataType = "String", example = "37553858")
	@Min(10)
	private String numberidentify;
	
	@ApiModelProperty(notes = "Institute is importan", dataType = "String", example = "cibertec")
	@NotNull(message = "Institute is important")
	private String  institute;
	
	
	private String description;
	
	


	private List<Students> parents;



	public Parents() {

	}

	public Parents(@NotNull(message = "id is important") String _id,@NotNull(message = "Complement Name is important") String complementName,
			@NotNull(message = "It is important") String sex, Date dateBirth,
			@NotNull(message = "Type of identity is important") String typeidentify, @Min(10) String numberidentify,@NotNull(message = "insitute is important") String institute,@NotNull(message = "description is important") String  description) {
		super();
		this._id=_id;
		this.complementName = complementName;
		this.sex = sex;
		this.dateBirth = dateBirth;
		this.typeidentify = typeidentify;
		this.numberidentify = numberidentify;
		this.institute=institute;
		this.description=description;
	}

	
	
	
	
	public List<Students> getParents() {
		return parents;
	}

	public void setParents(List<Students> parents) {
		this.parents = parents;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getComplementName() {
		return complementName;
	}

	public void setComplementName(String complementName) {
		this.complementName = complementName;
	}

	public String getSex() {

		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getTypeidentify() {
		return typeidentify;
	}

	public void setTypeidentify(String typeidentify) {
		this.typeidentify = typeidentify;
	}

	public String getNumberidentify() {
		return numberidentify;
	}

	public void setNumberidentify(String numberidentify) {
		this.numberidentify = numberidentify;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

}