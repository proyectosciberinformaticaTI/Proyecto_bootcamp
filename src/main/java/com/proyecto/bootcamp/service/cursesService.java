package com.proyecto.bootcamp.service;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Profesor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface cursesService {

	Mono<Curse> findByIdCurses(String id);

	Mono<Profesor> findByComplementNameProfesor(String _id);

	Flux<Curse> findDateBirthbetween(Date startDate, Date endDate);

	Mono<ResponseEntity<Flux<Curse>>> findAll();

	Mono<Map<String, Object>> save(@Valid @RequestBody Mono<Curse> curse);
	
	
	
	Mono<Map<String, Object>> update(@PathVariable("id") String id,@Valid @RequestBody Curse curse);

	Flux<Curse> findByNombreCurses(String nombre);

	Mono<Object> delete(String id);

	Mono<Profesor> findById(String id);

	Flux<Curse> findByProfesorAndEstado(String nombre, String estado);

}
