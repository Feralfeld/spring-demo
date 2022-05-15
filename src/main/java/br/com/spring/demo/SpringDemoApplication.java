package br.com.spring.demo;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

	Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Value("${variaveis.banco}")
	private String variavelBanco;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String get() {

		logger.info("Feralfeld");
		return "Feralfeld Test";
	}

	@RequestMapping(value = "/variavel", method = RequestMethod.GET)
	public String get4() {
		logger.info(variavelBanco);
		return variavelBanco;
	}

	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public String get3() {

		logger.info("DATE TIME ->" + new Date().toString());
		logger.info("Zoned Time " + LocalDateTime.now(ZoneId.of("America/Sao_Paulo")) );

		return new Date().toString();
	}


}
