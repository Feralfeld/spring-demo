package br.com.spring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

	Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String get() {

		logger.info("Hello world");
		 return "Hello World";
	}

	@RequestMapping(value = "/2", method = RequestMethod.GET)
	public String get2() {

		logger.info("Feralfeld");
		return "Feralfeld Test";
	}

}
