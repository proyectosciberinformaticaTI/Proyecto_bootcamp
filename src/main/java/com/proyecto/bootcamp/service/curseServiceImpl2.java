package com.proyecto.bootcamp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Profesor;
import com.proyecto.bootcamp.bean.Students;
import com.proyecto.bootcamp.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class curseServiceImpl2 implements curseService2{

	
 @Autowired	
 private ItemReactiveRepository repo;
	
	
	
	@Override
	public Mono<Parents> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}


}
