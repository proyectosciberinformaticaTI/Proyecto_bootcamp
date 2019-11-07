package com.proyecto.bootcamp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Parents;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CurseReactiveRepository extends ReactiveMongoRepository<Curse,String>{
	
	@Query("{ '_id': ?0 }")
	public Mono<Curse> findByIdCurses(String id);
	
	

	public Flux<Curse> findByNombre(String nombre);

	
	
	public Flux<Curse> findByProfesorAndEstado(String nombre, String estado);
}
