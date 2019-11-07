package com.proyecto.bootcamp.service;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.family;
import com.proyecto.bootcamp.bean.inscriptionCourse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface inscriptionCourseService {

	Mono<inscriptionCourse> findByCourseNombre(String nombre);

	Mono<ResponseEntity<Map<String, Object>>> guardarins(@Valid @RequestBody inscriptionCourse curse);

	Mono<ResponseEntity<Flux<inscriptionCourse>>> findAll() ;

	Mono<Object> delete(String id);

	Mono<inscriptionCourse> findById(String id);

}
