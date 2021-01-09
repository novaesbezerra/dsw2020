package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;

@SpringBootApplication
public class AgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ConsultaDAO consultaDAO, UsuarioDAO usuarioDAO, MedicoDAO medicoDAO, PacienteDAO pacienteDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {

			Usuario u1 = new Usuario("ADMIN", "admin", "admin", "admin");
			u1.setSenha(encoder.encode("admin"));
			usuarioDAO.save(u1);
			
			Medico m1 = new Medico("12345678", "Jose Maria", "123abc", "Clinico Geral", "jose_maria@gmail.com");
			m1.setSenha(encoder.encode("123abc"));
			medicoDAO.save(m1);
			Medico m2 = new Medico("87654321", "Joao Ciniro", "123abc", "Ginecologista", "ciniro@gmail.com");
			m2.setSenha(encoder.encode("123abc"));
			medicoDAO.save(m2);
			Medico m3 = new Medico("98765432", "Joaquim Zagatti", "123abc", "Cardiologista", "zagatti@gmail.com");
			m3.setSenha(encoder.encode("123abc"));
			medicoDAO.save(m3);

			Paciente p1 = new Paciente("Juraci Carvalho", "juraci@gmail.com", "123456", "12345678900", "12345-12345", "FEM", "1900-12-01");
			p1.setSenha(encoder.encode("123456"));
			pacienteDAO.save(p1);
			Paciente p2 = new Paciente("Juarez", "juarez@gmail.com", "123456", "12345678901", "12345-12345", "MASC", "1900-12-02");
			p2.setSenha(encoder.encode("123456"));
			pacienteDAO.save(p2);

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
