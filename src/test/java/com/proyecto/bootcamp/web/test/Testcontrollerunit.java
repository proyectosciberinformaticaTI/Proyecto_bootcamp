package com.proyecto.bootcamp.web.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.repository.ItemReactiveRepository;
import com.proyecto.bootcamp.service.parentsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class Testcontrollerunit {

//	
//	ObjectMapper mapper;
//
//	@Autowired
//	WebTestClient webTestClient;
//
//	@MockBean
//	private parentsService parentsService;
//
//	@BeforeEach
//	public void init() {
////		webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
//
//		mapper = new ObjectMapper();
//	}
//
//	@Test
//	public void testCreateParents() {
//        Parents parent = new Parents();
//        parent.set_id("4667485");
//        parent.setComplementName("Test");
//   
//        parent.setNumberidentify("35535");
//        parent.setSex("H");
//        parent.setTypeidentify("DNI");
//        Mockito.when(parentsService.save(parent)).thenReturn(Mono.just(parent));
// 
//        webTestClient.post()
//            .uri("/index/create")
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(BodyInserters.fromObject(parent))
//            .exchange()
//            .expectStatus().isCreated();
// 
//     
//	}
//
//	@Test
//	public void testGetParents() {
//
//
//		webTestClient.get().uri("/index/listall").accept(MediaType.APPLICATION_JSON_UTF8)
//		.exchange()
//		.expectStatus().isOk()
//		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//		.expectBodyList(Parents.class)
//		.consumeWith(response -> {
//			List<Parents> list = response.getResponseBody();
//			list.forEach(p -> {
//				System.out.println(p.getComplementName());
//			});
//			
//			Assertions.assertThat(list.size()>0).isTrue();
//		});
//
//	}
//
//	
//	
//	
//	

	@Autowired
	private WebTestClient client;

	@Autowired
	private parentsService service;

	@Test
	public void liststudents() {

		client.get().uri("/index/listall").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Parents.class)
				.consumeWith(response -> {
					List<Parents> pa = response.getResponseBody();
					pa.forEach(p -> {
						System.out.println(p.getComplementName());
					});

					Assertions.assertThat(pa.size() > 0).isTrue();
				});

	}

	@Test
	public void liststudents2() {

		client.get().uri("/index/listall").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Parents.class)
				.consumeWith(response -> Assertions.assertThat(response.getResponseBody()).isNotNull());

	}

	@Test
	public void listidparents() {

		client.get().uri("/index/consultaid/" + "{id}", Collections.singletonMap("id", "5dadfe34f30b3c23ec3c61ef"))
				.accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8).expectBody(Parents.class);

	}

	@Test
	public void createstudent() {

		Parents parent = new Parents("5dadfe34f30b3c23ec3c61ef", "Test", "m", new Date(), "DNI", "483585389",
				"cibertec", "estudiante");

		client.post().uri("/index/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(parent), Parents.class).exchange()
				.expectStatus().is5xxServerError().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody().jsonPath("$.complementName", is("test"));

	}

	@Test
	public void createstudent2() {

		Parents parent = new Parents("5dadfe34f30b3c23ec3c61ef", "Joel", "m", new Date(), "DNI", "483585389",
				"cibertec", "estudiante");

		client.post().uri("/index/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(parent), Parents.class).exchange()
				.expectStatus().is5xxServerError().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody(new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {
				}).consumeWith(response -> {
					Assert.assertEquals("mensaje esperado", HttpStatus.SC_INTERNAL_SERVER_ERROR,
							response.getStatus().value());

				});
	}

	@Test
	public void eliminarTest() {

		client.delete().uri("/index/eliminarstudent/" + "{id}", "5dadfe34f30b3c23ec3c61ef").exchange().expectStatus()
				.isNoContent().expectBody(Void.class);

		client.get().uri("/index/consultaid/{id}", "5dadfe34f30b3c23ec3c61ef").exchange().expectStatus().isNotFound()
				.expectBody().isEmpty();
	}

	@Test
	public void actualizar() {

		Parents parent = new Parents("5dadfe34f30b3c23ec3c61eg", "Joel", "m", new Date(), "DNI", "483585389",
				"cibertec", "estudiante");

		client.put().uri("/{id}", "5dadfe34f30b3c23ec3c61ef").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(parent), Parents.class).exchange()
				.expectStatus().isCreated().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).expectBody()
				.jsonPath("$._id").isNotEmpty();

	}
	
	
	
	
	
	
	
	
	

}
