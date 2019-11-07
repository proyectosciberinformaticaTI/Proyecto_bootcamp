package com.proyecto.bootcamp;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.format.annotation.DateTimeFormat;

import com.proyecto.bootcamp.bean.Parents;
import com.proyecto.bootcamp.bean.Students;
import com.proyecto.bootcamp.service.parentsService;
import com.proyecto.bootcamp.service.parentsServiceImpl;
import com.proyecto.bootcamp.service.studentsService;



import io.swagger.annotations.ApiModelProperty;
import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
public class ProyectoBootcampApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoBootcampApplication.class, args);
	}

	
	
}
