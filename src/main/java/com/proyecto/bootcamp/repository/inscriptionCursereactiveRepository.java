package com.proyecto.bootcamp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.inscriptionCourse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface inscriptionCursereactiveRepository extends ReactiveMongoRepository<inscriptionCourse,String>{

	
	
	@Query("'course' : ?0}")
	public Mono<inscriptionCourse> findByCourseNombre(String nombre);
	





}
