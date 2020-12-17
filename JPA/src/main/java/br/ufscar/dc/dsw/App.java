package br.ufscar.dc.dsw;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.GenericDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;

public class App {

	public static void main(String[] args) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		ConsultaDAO consultaDAO = new ConsultaDAO();

		MedicoDAO medicoDAO = new MedicoDAO();
		
/*
		Departamento dc = new Departamento("Computação", "DC");

		System.out.println("Salvando Departamento - DC");

		departamentoDAO.save(dc);
		
		*/

		Medico medico1 = new Medico("123456", "Cardiologista");

		System.out.println("Salvando Medico 1");

		usuarioDAO.save(medico1);

		Medico medico2 = new Medico("111222", "Clinico Geral");

		System.out.println("Salvando Medico 2");

		usuarioDAO.save(medico2);
		
		/*

		Disciplina dsw = new Disciplina("Desenvolvimento Web 1", "DSW", professor1);

		System.out.println("Salvando Disciplina - DSW");

		disciplinaDAO.save(dsw);

		Aluno aluno1 = new Aluno("Aluno 1", "123456");

		System.out.println("Salvando Aluno 1");

		pessoaDAO.save(aluno1);

		Aluno aluno2 = new Aluno("Aluno 2", "654321");

		System.out.println("Salvando Aluno 2");

		pessoaDAO.save(aluno2);

		List<Pessoa> pessoas = pessoaDAO.findAll();

		System.out.println("Imprimindo pessoas - findAll()");

		for (Pessoa p : pessoas) {
			System.out.println(p);
		}

		// Matricula aluno1 na disciplina

		Set<Disciplina> disciplinas = new HashSet<>();
		disciplinas.add(dsw);
		aluno1.setDisciplinas(disciplinas);

		pessoaDAO.update(aluno1);

		// Matricula aluno2 na disciplina

		disciplinas = new HashSet<>();
		disciplinas.add(dsw);
		aluno2.setDisciplinas(disciplinas);

		pessoaDAO.update(aluno2);

		System.out.println("Imprimindo alunos da disciplina dsw");

		dsw = disciplinaDAO.find(1L);

		for (Aluno a : dsw.getAlunos()) {
			System.out.println(a);
		}

		System.out.println("Imprimindo professores do departamento dc");

		for (Professor p : professorDAO.findbyDepartamento(dc)) {
			System.out.println(p);
		}
*/
		GenericDAO.close();
	}
}