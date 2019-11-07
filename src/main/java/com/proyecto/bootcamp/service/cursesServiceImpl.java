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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.bootcamp.bean.Curse;
import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Profesor;
import com.proyecto.bootcamp.repository.CurseReactiveRepository;
import com.proyecto.bootcamp.repository.ProfesorRepository;
import com.proyecto.bootcamp.web.CurseController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class cursesServiceImpl implements cursesService {

	private static final Logger log = LoggerFactory.getLogger(cursesServiceImpl.class);
	@Autowired
	private CurseReactiveRepository repo;

	@Autowired
	private ProfesorRepository prorepo;

	public Mono<Curse> findByComplementName(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<Curse> findByNumberidentify(String numberidentify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Curse> findDateBirthbetween(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseEntity<Flux<Curse>>> findAll() {
		// TODO Auto-generated method stub
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(repo.findAll()));
	}

	@Override
	public Mono<Map<String, Object>> save(@Valid @RequestBody Mono<Curse> curse) {
		// TODO Auto-generated method stub
		Map<String, Object> respuesta = new HashMap<String, Object>();

		return curse.flatMap(po -> {

			if (Integer.parseInt(po.getMaximo()) < Integer.parseInt(po.getMinimo())) {
				respuesta.put("Error", "Rango equivocado");
				respuesta.put("error en el campo maximo", po.getMaximo());
				respuesta.put("erro en el campo minimo", po.getMinimo());
			} else {

				return repo.findByProfesorAndEstado(po.getProfesor(), po.getEstado()).collectList().flatMap(pl -> {
					if (po.getEstado().equals("no activo")) {
						respuesta.put("ERROR", "registro no activo");
					}

//					for(int i = 0; i < pl.size(); i++) {
//						if (po.getNombre().equals(pl.get(i).getNombre())) {
//							respuesta.put("Registro", "el curso ya existe");
//						}
//					}

					// && pl.size() < 3
					if (po.getEstado().equals("activo")) {
						respuesta.put("Operacion", "REGISTRO EXITOSO");
						respuesta.put("Datos", "INGRESADOS CORRECATAMENTE");
						respuesta.put("Informacion de datos", po);
						repo.save(po).map(ds -> {

							return ResponseEntity.created(URI.create("/index/".concat(ds.get_id())))
									.contentType(MediaType.APPLICATION_JSON_UTF8).body(respuesta);
						}).subscribe();

					}

					return Mono.just(respuesta);

				});

			}
			return Mono.just(respuesta);

//			respuesta.put("Registro", "exitoso");
//			respuesta.put("INFO", po);
//
//			return service.save(po);

		}).doOnError(pl -> {
			log.debug("Mensaje error" + pl.getMessage());
			log.debug("Causa del error" + pl.getCause());
		}).switchIfEmpty(Mono.error(() -> new Exception("Error en el campo vacio")))
				.doOnCancel(() -> log.debug("campo vacio"));
	}

	@Override
	public Mono<Profesor> findById(String id) {
		// TODO Auto-generated method stub
		return prorepo.findById(id);
	}

	@Override
	public Mono<Object> delete(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> respuesta = new HashMap<String, Object>();
		return repo.findByIdCurses(id).defaultIfEmpty(new Curse()).flatMap(p -> {
			if (p.get_id() == null) {

				return Mono.error(new InterruptedException("No extiste el curso a eliminar! " + id));
			}

			return Mono.just(p);
		}).flatMap(p -> {
			log.info("Eliminando el curso: " + p.getNombre());
			log.info("Eliminando curso Id: " + p.get_id());
			return repo.delete(p);
		});

	}

	@Override
	public Mono<Curse> findByIdCurses(String id) {
		// TODO Auto-generated method stub
		return repo.findByIdCurses(id);
	}

	@Override
	public Mono<Profesor> findByComplementNameProfesor(String _id) {
		// TODO Auto-generated method stub
		return prorepo.findByComplementNameProfesor(_id);
	}

	@Override
	public Flux<Curse> findByNombreCurses(String nombre) {
		// TODO Auto-generated method stub
		return repo.findByNombre(nombre);
	}

	@Override
	public Flux<Curse> findByProfesorAndEstado(String nombre, String estado) {
		// TODO Auto-generated method stub
		return repo.findByProfesorAndEstado(nombre, estado);
	}

	public Mono<Map<String, Object>> update(@PathVariable("id") String id, @Valid @RequestBody Curse curse) {
		// TODO Auto-generated method stub

		Map<String, Object> resp = new HashMap<String, Object>();

		return repo.findByIdCurses(id).map(resultado -> {
	
			if (Integer.parseInt(resultado.getMaximo()) < Integer.parseInt(resultado.getMinimo())) {
				resp.put("Error", "Rango equivocado");
				resp.put("error en el campo maximo", resultado.getMaximo());
				resp.put("erro en el campo minimo", resultado.getMinimo());
			} else {
				resultado.setEstado(curse.getEstado());
				resultado.setMaximo(curse.getMaximo());
				resultado.setMinimo(curse.getMinimo());
				resultado.setNombre(curse.getNombre());
				resultado.setProfesor(curse.getProfesor());
				resp.put("Informacion", resultado);
				repo.save(resultado);
			}
			return resp;
		}).doOnError(pl -> {
			log.debug("Mensaje error" + pl.getMessage());
			log.debug("Causa del error" + pl.getCause());
		}).switchIfEmpty(Mono.error(() -> new Exception("Error en el campo vacio")))
				.doOnCancel(() -> log.debug("campo vacio"));
	}

}
