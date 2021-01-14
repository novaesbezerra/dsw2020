package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests();
        http.authorizeRequests()
		.antMatchers("/consultas", "/medicos", "/pacientes").permitAll() // REST Controllers (GET - get All, POST - Create)
		.antMatchers("/consultas/{\\d+}", "/medicos/{\\d+}", "/pacientes/{\\d+}").permitAll() // REST Controllers (GET by id, PUT - Update by id, DELETE by ID)
		.antMatchers("/medicos/consulta/{\\w+}").permitAll() // REST Controllers (GET by titulo)
		.antMatchers("/medicos/especialidades/{\\d+}").permitAll() // REST Controllers (GET by UsuarioID)
		.anyRequest().authenticated();
	}

}