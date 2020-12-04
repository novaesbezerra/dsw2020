package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.PacienteDAO;
//import br.ufscar.dc.dsw.domain.Long;
import br.ufscar.dc.dsw.domain.Paciente;
//import br.ufscar.dc.dsw.domain.String;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PacienteDAO dao;

	@Override
	public void init() {
		dao = new PacienteDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteLogado");
		Erro erros = new Erro();

		if (paciente == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (paciente.getId().equals(1000)) {  /////OLHAR ESSA PAGINA
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}

		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
			case "/cadastro":
				apresentaFormCadastro(request, response);
				break;
			case "/insercao":
				insere(request, response);
				break;
			case "/remocao":
				remove(request, response);
				break;
			case "/edicao":
				apresentaFormEdicao(request, response);
				break;
			case "/atualizacao":
				atualize(request, response);
				break;
			default:
				lista(request, response);
				break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Paciente> listaPacientes = dao.getAll();
		request.setAttribute("listaPacientes", listaPacientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Paciente paciente = dao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/formulario.jsp");
		request.setAttribute("paciente", paciente);
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Long cpf = Long.parseLong(request.getParameter("cpf"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		String nascimento = request.getParameter("nascimento");
		String senha = request.getParameter("senha");

		Paciente paciente = new Paciente(nome, email, senha, cpf, telefone, sexo, nascimento);

		dao.insert(paciente);
		response.sendRedirect("lista");
	}

	private void atualize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		Long cpf = Long.parseLong(request.getParameter("cpf"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		String nascimento = request.getParameter("nascimento");
		String senha = request.getParameter("senha");

		Paciente paciente = new Paciente(id, nome, email, senha, cpf, telefone, sexo, nascimento);

		dao.update(paciente);
		response.sendRedirect("lista");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		Paciente paciente = new Paciente(id);
		dao.delete(paciente);
		response.sendRedirect("lista");
	}
}