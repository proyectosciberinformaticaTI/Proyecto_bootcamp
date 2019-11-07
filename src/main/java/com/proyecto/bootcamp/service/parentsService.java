package com.proyecto.bootcamp.service;


import java.util.Date;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Students;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface parentsService {

	
//	Flux<Parents> findByDateBirthBetween();
	
	Mono<Parents> findByComplementName(String nombre);
	
	Mono<Parents> findByNumberidentify(String numberidentify);
	
 Flux<Parents> findDateBirthbetween(Date startDate, Date endDate);
	
 Flux<Parents> findAll();
	
	
	Mono<Parents> save(Parents parent);
	


    Mono<Void> delete(Parents parent);
    
   Mono<Parents> findById(String id);
   
   
 Flux<Parents>  findidnumber(String id);
 
 Flux<Parents> findByComplementNameAndDescription(String complementname,String description);
 
 
 Flux<Parents> findByComplementNameAndDescriptionTeacher(String complementname,String description);

}
