package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@SpringBootApplication
public class ProjetBilleterieSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetBilleterieSpringApplication.class, args);

	}

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
		return new JdbcUserDetailsManager(dataSource);
	}
}


