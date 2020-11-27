package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EspecialidadeDAO;
import br.ufscar.dc.dsw.domain.Especialidade;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/especialidades/*")
public class EspecialidadeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private EspecialidadeDAO dao;

	@Override
	public void init() {
		dao = new EspecialidadeDAO();
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
		} else if (!paciente.getPapel().equals("ADMIN")) {
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
		List<Especialidade> listaEspecialidades = dao.getAll();
		request.setAttribute("listaEspecialidades", listaEspecialidades);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/especialidade/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/especialidade/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Especialidade especialidade = dao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/especialidade/formulario.jsp");
		request.setAttribute("especialidade", especialidade);
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String CNPJ = request.getParameter("CNPJ");
		String nome = request.getParameter("nome");

		Especialidade especialidade = new Especialidade(CNPJ, nome);

		dao.insert(especialidade);
		response.sendRedirect("lista");
	}

	private void atualize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		String CNPJ = request.getParameter("CNPJ");
		String nome = request.getParameter("nome");

		Especialidade especialidade = new Especialidade(id, CNPJ, nome);

		dao.update(especialidade);
		response.sendRedirect("lista");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		Especialidade especialidade = new Especialidade(id);
		dao.delete(especialidade);
		response.sendRedirect("lista");
	}
}