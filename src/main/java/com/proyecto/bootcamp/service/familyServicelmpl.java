package com.proyecto.bootcamp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bootcamp.bean.family;
import com.proyecto.bootcamp.repository.FamilyReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class familyServicelmpl  implements familyService{

	 
	@Autowired
	private FamilyReactiveRepository repository;
	
	
	
	
	
	
	
	
	
	@Override
	public Mono<family> findByComplementName(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<family> findByNumberidentify(String numberidentify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<family> findDateBirthbetween(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<family> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<family> save(family parent) {
		// TODO Auto-generated method stub
		return repository.save(parent);
	}

	@Override
	public Mono<Void> delete(family parent) {
		// TODO Auto-generated method stub
		return delete(parent);
	}

	@Override
	public Mono<family> findById(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

}
