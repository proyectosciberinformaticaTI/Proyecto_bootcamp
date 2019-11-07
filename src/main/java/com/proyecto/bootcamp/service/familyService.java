package com.proyecto.bootcamp.service;

import java.util.Date;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.family;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface familyService {

	Mono<family> findByComplementName(String nombre);

	Mono<family> findByNumberidentify(String numberidentify);

	Flux<family> findDateBirthbetween(Date startDate, Date endDate);

	Flux<family> findAll();

	Mono<family> save(family parent);

	Mono<Void> delete(family parent);

	Mono<family> findById(String id);

}
