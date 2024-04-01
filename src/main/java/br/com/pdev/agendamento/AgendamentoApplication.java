package br.com.pdev.agendamento;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Sistema de agendamento de consulta",
				version = "1.0",
				description = "Documentando uma API básica de gerenciamento de pessoas",
				contact = @Contact(name = "Carolina Mesquita", email = "carolti2013@gmail.com", url = "https://github.com/carolinachm/agendamento")
		)
)
public class AgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoApplication.class, args);
	}

}
