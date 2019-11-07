package com.proyecto.bootcamp.service;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.inscriptionCourse;
import com.proyecto.bootcamp.repository.inscriptionCursereactiveRepository;

import io.swagger.models.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class inscriptionCourseServiceImpl implements inscriptionCourseService{

	
	
	
	@Autowired
	private inscriptionCursereactiveRepository repo;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(inscriptionCourseServiceImpl.class);
	
	
	
	@Override
	public Mono<inscriptionCourse> findByCourseNombre(String nombre) {
		// TODO Auto-generated method stub
		return repo.findByCourseNombre(nombre);
	}







	@Override
	public Mono<ResponseEntity<Map<String, Object>>> guardarins(@Valid @RequestBody inscriptionCourse curse){
		// TODO Auto-generated method stub
		Map<String, Object> respuesta = new HashMap<String, Object>();

//		Mono<Curse> course = cuservice.findByIdCurses(curse.getCourse().get_id());

		Mono<inscriptionCourse> cp = Mono.just(curse);

		return cp.flatMap(pl -> {
//			Map<String, Object> envio = new HashMap<String, Object>();
//			envio.put("codigo", pl.get_id());
//			return insservice.findByCourseNombre(pl.getCourse()).map(tr -> {
//				respuesta.put("error en la inscr", "El curso ya tiene");
//				return Mono.just(respuesta);
//			});
			
			
			
			if (curse.getEstado().equals("activo")) {
				
				return repo.save(pl).map(po -> {
					respuesta.put("REGISTRO EXITOSO", curse);
					return ResponseEntity.created(URI.create("/curse/".concat(po.get_id())))
							.contentType(MediaType.APPLICATION_JSON_UTF8).body(respuesta); 

				
	
				})	;
			}

			if (curse.getEstado().equals("no activo")) {
				respuesta.put("ERROR", "REGISTRO NO ACTIVO");
				return Mono.just(ResponseEntity.badRequest().body(respuesta));
			} else {
				return Mono.just(ResponseEntity.badRequest().body(respuesta));
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}).onErrorResume(t -> {
		return Mono.just(t).cast(WebExchangeBindException.class).flatMap(e -> Mono.just(e.getFieldErrors()))
			.flatMapMany(Flux::fromIterable)
			.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
			.collectList().flatMap(list -> {
				respuesta.put("errors", list);
				respuesta.put("timestamp", new Date());
				respuesta.put("status", HttpStatus.BAD_REQUEST.value());
				return Mono.just(ResponseEntity.badRequest().body(respuesta));
			});
		
		});

//					insservice.findByCourseNombre(curse.getCourse()).map(k->{
//				if(k.getCourse()==null) {
//					respuesta.put("Curso", "no existe");
//				}if(k.getCourse()!=null) {
//					respuesta.put("registro","nuevo");
//				}
//				return respuesta;
//			});

	}

	@Override
	public Mono<ResponseEntity<Flux<inscriptionCourse>>> findAll() {
		// TODO Auto-generated method stub
		return Mono.just(ResponseEntity.ok().body(repo.findAll()));
	}





	
	
	
	


	@Override
	public Mono<Object> delete(String id){
		// TODO Auto-generated method stub
		Map<String, Object> respuesta = new HashMap<String, Object>();
		return repo.findById(id).defaultIfEmpty(new inscriptionCourse()).flatMap(p -> {
			if (p.get_id() == null) {

				return Mono.error(new InterruptedException("No extiste la inscripcion a eliminar! " + id));
			}

			return Mono.just(p);
		}).flatMap(p -> {
			log.info("Eliminando la inscripcion: " + p.getNombre());
			log.info("Eliminando inscripcion Id: " + p.get_id());
			return repo.delete(p);
		});
	}







	@Override
	public Mono<inscriptionCourse> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}



	
	
	
	
}
