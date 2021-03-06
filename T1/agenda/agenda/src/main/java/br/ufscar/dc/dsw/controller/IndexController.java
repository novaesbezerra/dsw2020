package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private MedicoDAO medico_dao;
	private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        medico_dao = new MedicoDAO();
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("email não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				AdminDAO adm = new AdminDAO();
				Admin admin = adm.getbyLogin(email);
				PacienteDAO pac = new PacienteDAO();
				Paciente paciente = pac.getbyEmail(email);
				MedicoDAO med = new MedicoDAO();
				Medico medico = med.getbyEmail(email);
				if(admin != null) {
					if (admin.getSenha().equals(senha)) {
						request.getSession().setAttribute("adminLogado", admin);
						response.sendRedirect("admin/");
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else if (paciente != null) {
					if (paciente.getSenha().equals(senha)) {
						request.getSession().setAttribute("pacienteLogado", paciente);
						request.getSession().setAttribute("pacienteIdLogado", paciente.getId());
						response.sendRedirect("consultas/");
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else if (medico != null) {
					if (medico.getsenha().equals(senha)) {
						request.getSession().setAttribute("medicoLogado", medico);
						response.sendRedirect("consultas/por_medico");
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
			request.getSession().invalidate();

			request.setAttribute("mensagens", erros);

			String URL = "/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
		} else if (request.getParameter("lista_sem_login") != null) {
			lista(request, response);
			return;
		}
		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}


    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> listaMedicos = medico_dao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_medico_no_auth.jsp");
        dispatcher.forward(request, response);
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}