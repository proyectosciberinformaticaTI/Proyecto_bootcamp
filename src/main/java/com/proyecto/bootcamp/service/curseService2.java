package com.proyecto.bootcamp.service;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Profesor;
import com.proyecto.bootcamp.bean.Students;

import reactor.core.publisher.Mono;

public interface curseService2 {
    
   Mono<Parents> findById(String id);
	

}
