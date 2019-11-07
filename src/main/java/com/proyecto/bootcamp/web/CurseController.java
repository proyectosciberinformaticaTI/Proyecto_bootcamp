package com.proyecto.bootcamp.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CancellationException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Endpoints;
import org.springframework.cloud.client.loadbalancer.reactive.CompletionContext.Status;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.async.client.Subscription;
import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Profesor;
import com.proyecto.bootcamp.bean.Students;
import com.proyecto.bootcamp.bean.family;
import com.proyecto.bootcamp.bean.inscriptionCourse;
import com.proyecto.bootcamp.service.curseServiceImpl2;
import com.proyecto.bootcamp.service.cursesService;
import com.proyecto.bootcamp.service.cursesServiceImpl;
import com.proyecto.bootcamp.service.familyServicelmpl;
import com.proyecto.bootcamp.service.inscriptionCourseService;
import com.proyecto.bootcamp.service.parentsServiceImpl;
import com.proyecto.bootcamp.service.studentsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/curse")
@Api(value = "REGISTER FAMILY")
public class CurseController {

	private cursesService cuservice;

	private inscriptionCourseService insservice;

	
	
	private WebClient client;
	
	@Autowired
	public CurseController(cursesService cuservice, inscriptionCourseService insservice,WebClient.Builder webClientBuilder) {
		this.cuservice = cuservice;
		this.insservice = insservice;
		this.client=webClientBuilder.baseUrl("http://localhost:8080").build();
	
	}

	private static final Logger log = LoggerFactory.getLogger(CurseController.class);

	@PutMapping(value = "/updatecurses/{id}")
	@ApiOperation(value = "UPDATE CURSES", response = Curse.class, httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = Curse.class),
			@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
	public Mono<Map<String, Object>> updatess(@PathVariable("id") String id, @Valid @RequestBody Curse curse) {

		return cuservice.update(id, curse);
	}

	@PostMapping(value = "/create")
	@ApiOperation(value = "CREATE  REGISTER CURSE", response = Parents.class, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = Curse.class),
			@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Map<String, Object>> guardar(@Valid @RequestBody Mono<Curse> curse) {

//		HashMap<String, Object> respuesta = new HashMap<String, Object>();
//
//		Mono<Profesor> profesor = service.findById(curse.getProfesor().get_id());
//		return profesor.flatMap(c -> {
//			
//			
//			
//			if (c.get_id().toString().length() < 25) {
////					for (List<Profesor> d : c) {
////						
////				
////				for(int i=0;i<) {
////				if(d.get(index)) {
////					
////					
////					
////				}
////				}
////				
////					}	
//				
//				if (curse.getEstado().equals("activo")) {
//					curse.setProfesor(c);
//                    
//					return service.save(curse).map(po -> {
//						respuesta.put("REGISTRO EXITOSO", curse);
//						return ResponseEntity.created(URI.create("/curse/".concat(po.get_id())))
//								.contentType(MediaType.APPLICATION_JSON_UTF8).body(respuesta); 
//
//					
//						
//						
//					
//					})	;
//				}
//
//				if (curse.getEstado().equals("no activo")) {
//					respuesta.put("ERROR", "REGISTRO NO ACTIVO");
//					return Mono.just(ResponseEntity.badRequest().body(respuesta));
//				} else {
//					return Mono.just(ResponseEntity.badRequest().body(respuesta));
//				}
//			}
//			return Mono.just(ResponseEntity.ok().body(respuesta));
//
//		}).onErrorResume(t -> {
//			return Mono.just(t).cast(WebExchangeBindException.class).flatMap(e -> Mono.just(e.getFieldErrors()))
//					.flatMapMany(Flux::fromIterable)
//					.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
//					.collectList().flatMap(list -> {
//						respuesta.put("errors", list);
//						respuesta.put("timestamp", new Date());
//						respuesta.put("status", HttpStatus.BAD_REQUEST.value());
//						return Mono.just(ResponseEntity.badRequest().body(respuesta));
//					});
//		}).defaultIfEmpty(ResponseEntity.notFound().build());

		return cuservice.save(curse).doOnError(t -> log.error(t.getMessage(), t));
	}

	@GetMapping(value = "/listallofcurses")
	@ApiOperation(value = "LIST ALL CURSE", response = Curse.class, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = Curse.class),
			@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
	public Mono<ResponseEntity<Flux<Curse>>> findAll() {
		return cuservice.findAll().doOnError(p -> {
			log.debug("Mensaje de error" + p.getMessage());
			log.debug("Causa del error" + p.getCause());
		});

	}

	@DeleteMapping(value = "/deletecurses/{id}")
	@ApiOperation(value = "LIST ALL CURSE", response = Curse.class, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = Curse.class),
			@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
	public Mono<Object> delete(@PathVariable("id") String id) {

		Map<String, Object> respuesta = new HashMap<String, Object>();
		return cuservice.delete(id).doOnSuccess(pl -> {
			log.debug("Registro eliminado");
		});

	}

	//////////////////////////////////////////// INSCRIPTION
	//////////////////////////////////////////// COURSE//////////////////////////////////////////7

	@PostMapping(value = "/createinscription")
	@ApiOperation(value = "CREATE  INSCRIPTION	 CURSE", response = inscriptionCourse.class, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = Curse.class),
			@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ResponseEntity<Map<String, Object>>> guardarins(@Valid @RequestBody inscriptionCourse curse) {
		
		
		return insservice.guardarins(curse).doOnError(p->{
			log.debug("Mensaje de error " + p.getMessage());
			log.debug("Causa del error " + p.getCause());
		
		
		
		});
		
	}
		
		
		
		
		@GetMapping(value = "/listallinscription")
		@ApiOperation(value = "LIST INSCRIPTION	 CURSE", response = inscriptionCourse.class, httpMethod = "GET")
		@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = inscriptionCourse.class),
				@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
				@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
				@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
		public Mono<ResponseEntity<Flux<inscriptionCourse>>> findAllinscosurse(){
			
			
			
			return insservice.findAll().doOnError(p->{
				log.debug("mensaje de error " + p.getMessage());
				log.debug("causa del error" + p.getCause());
			});
		}
		
		
		
		
		
		
		@DeleteMapping(value = "/deleteinscriptioncurses/{id}")
		@ApiOperation(value = "DELETE INSCRIPTION CURSE", response = inscriptionCourse.class, httpMethod = "GET")
		@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.", response = inscriptionCourse.class),
				@ApiResponse(code = 400, message = "Envio datos incorrectos.", response = ExceptionInInitializerError.class),
				@ApiResponse(code = 404, message = "No existe una entidad con ese ID.", response = ExceptionInInitializerError.class),
				@ApiResponse(code = 500, message = "Error inesperado.", response = ExceptionInInitializerError.class) })
		public Mono<Object> deleteinscription(@PathVariable("id") String id) {

		
		
		
			return insservice.delete(id).doOnSuccess(pl -> {
				log.debug("Registro eliminado");
			});
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
