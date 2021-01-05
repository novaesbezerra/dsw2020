package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class AgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ConsultaDAO consultaDAO, UsuarioDAO usuarioDAO) {
		return (args) -> {

			Usuario u1 = new Usuario();
			u1.setNome("usuario1");
			u1.setEmail("usuario1@gmail.com");
			u1.setSenha("123456");
			usuarioDAO.save(u1);

			Usuario u2 = new Usuario();
			u2.setNome("usuario2");
			u2.setEmail("usuario2@gmail.com");
			u2.setSenha("123456");
			usuarioDAO.save(u2);

			Usuario u3 = new Usuario();
			u3.setNome("usuario3");
			u3.setEmail("usuario3@gmail.com");
			u3.setSenha("123456");
			usuarioDAO.save(u3);

			Consulta c1 = new Consulta();
			c1.setHora("12h30");
			c1.setData("12/12/20");
			c1.setMedico(m1);
			c1.setPaciente(p1);
			consultaDAO.save(c1);

			Consulta c2 = new Consulta();
			c2.setHora("14h30");
			c2.setData("12/12/20");
			c2.setMedico(m1);
			c2.setPaciente(p2);
			consultaDAO.save(c2);

			Consulta c3 = new Consulta();
			c3.setHora("10h30");
			c3.setData("12/12/20");
			c3.setMedico(m2);
			c3.setPaciente(p1);
			consultaDAO.save(c3);
		};
	}
}
