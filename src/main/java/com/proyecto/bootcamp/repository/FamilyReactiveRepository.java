package com.proyecto.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.bootcamp.bean.family;



@Repository
public interface FamilyReactiveRepository extends ReactiveMongoRepository<family, String>{

}
