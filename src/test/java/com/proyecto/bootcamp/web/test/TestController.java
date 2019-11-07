package com.proyecto.bootcamp.web.test;

import static org.assertj.core.api.Assertions.in;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.BodyInserters;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;
import com.proyecto.bootcamp.OrderServiceConfig;
import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.repository.ItemReactiveRepository;
import com.proyecto.bootcamp.service.parentsService;
import com.proyecto.bootcamp.web.studentsController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = studentsController.class)
@ContextConfiguration(classes = { OrderServiceConfig.class })
public class TestController {

	ObjectMapper mapper;

	@Autowired
	WebTestClient webTestClient;

	@Mock
	private ItemReactiveRepository parentsService;

	@BeforeEach
	public void init() {
		webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

		mapper = new ObjectMapper();
	}

	@Test
	public void testCreateParents() {
		Parents parent = new Parents();
		try {

			parent.set_id("5db0ba05f30b3c075069fba3");
			parent.setComplementName("Test");
			parent.setDateBirth(new Date());
			parent.setDescription("fjfj");
			parent.setInstitute("cibertec");
			parent.setNumberidentify("354646");
			parent.setTypeidentify("DNI");
			parent.setSex("m");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		Mockito.when(parentsService.save(parent)).thenReturn(Mono.just(parent));

		webTestClient.post().uri("/index/create").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(parent)).exchange().expectStatus().is5xxServerError();

		Mockito.verify(parentsService, times(1)).save(parent);
	}

	@Test
	public void testGetParents() {

		Parents parent = new Parents();

		parent.set_id("5db0ba05f30b3c075069fba3");
		parent.setComplementName("Test");
		parent.setNumberidentify("354646");
		parent.setTypeidentify("DNI");
		parent.setSex("m");

		Parents parent2 = new Parents();

		parent2.set_id("5db0ba05f30b3c075069fba3");
		parent2.setComplementName("Joel Arauzo");
		parent2.setNumberidentify("35464656");
		parent2.setTypeidentify("DNI");
		parent2.setSex("m");
		Flux<Parents> statementFlux = Flux.just(parent, parent2);

		Mockito.when(parentsService.findAll()).thenReturn(statementFlux);

		webTestClient.get().uri("/index/listall").exchange().expectStatus().isOk().expectBody(Void.class)
				.returnResult();

	}

	@Test
	public void deletebyid() {
//		Parents parents = new Parents();
//		parents.set_id("46337584");
//
//		Mono<Void> voidReturn = Mono.empty();
//		Mockito.when(parentsService.delete(parents).thenReturn(voidReturn));
//
//		webTestClient.get().uri("/{id}", Collections.singletonMap("id", parents.get_id())).exchange().expectStatus()
//				.isNoContent().expectBody().isEmpty();

	}

	@Test
	public void getById() {

		Parents pa = new Parents();
		pa.set_id("5daa0761f30b3c46dc9bbb0e");

		Mockito.when(parentsService.findById("5daa0761f30b3c46dc9bbb0e")).thenReturn(Mono.just(pa));

		webTestClient.get().uri("/{id}", "5daa0761f30b3c46dc9bbb0e").exchange().expectStatus().isNotFound().expectBody()
				.jsonPath("$._id", is(equals(pa)));

	}

}
