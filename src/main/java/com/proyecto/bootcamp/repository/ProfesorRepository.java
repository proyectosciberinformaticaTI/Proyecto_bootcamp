package com.proyecto.bootcamp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Profesor;

import reactor.core.publisher.Mono;

@Repository
public interface ProfesorRepository extends ReactiveMongoRepository<Profesor, String>





{

	
@Query("{ '_id': ?0 }")
public	Mono<Profesor> findByComplementNameProfesor(String _id);

}
