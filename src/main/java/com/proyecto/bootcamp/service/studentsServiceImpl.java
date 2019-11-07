package com.proyecto.bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Students;
import com.proyecto.bootcamp.repository.ParentsReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class studentsServiceImpl implements studentsService{

	@Autowired
	private ParentsReactiveRepository repository;
	
	
	
	
	@Override
	public Mono<Students> save(Students parent) {
		// TODO Auto-generated method stub
		return repository.save(parent);
	}

	@Override
	public Flux<Students> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<Students> findById(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Mono<Void> delete(Students parent) {
		// TODO Auto-generated method stub
		return repository.delete(parent);
	}

	@Override
	public Mono<Students> findBycomplementnamefamiliaries(String complementName) {
		// TODO Auto-generated method stub
		return repository.findBycomplementnamefamiliaries(complementName);
	}

}
