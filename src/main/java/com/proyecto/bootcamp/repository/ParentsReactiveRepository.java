package com.proyecto.bootcamp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Students;

import reactor.core.publisher.Mono;

@Repository
public interface ParentsReactiveRepository extends ReactiveMongoRepository<Students,String>{

	
	
	
	
	@Query("{ 'relation': ?0 }")
	public Mono<Students> findBycomplementnamefamiliaries(String complementName);
	
}
