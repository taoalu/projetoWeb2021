package br.com.projetoweb.projetoweb;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projetoweb.projetoweb.model.Usuario;

@SpringBootApplication
public class ProjetowebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetowebApplication.class, args);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		 return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

    @Bean
    public AuditorAware<Usuario> auditor() {
	return () -> Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
		.filter(Authentication::isAuthenticated).map(Authentication::getPrincipal).map(Usuario.class::cast);
    }
}
