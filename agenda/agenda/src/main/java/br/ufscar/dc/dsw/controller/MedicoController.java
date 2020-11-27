package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/medicos/*")
public class MedicoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MedicoDAO dao;

    @Override
    public void init() {
        dao = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteLogado");
		Erro erros = new Erro();

		if (paciente == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!paciente.getId().equals(1)) {
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
        List<Medico> listaMedicos = dao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Medico medico = dao.get(id);
        request.setAttribute("medico", medico);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long crm = Long.parseLong(request.getParameter("crm"));
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String especialidade = request.getParameter("especialidade");
        String email = request.getParameter("email");


        Medico medico = new Medico(crm, nome, senha, especialidade, email);
        dao.insert(medico);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        Long crm = Long.parseLong(request.getParameter("crm"));
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String especialidade = request.getParameter("especialidade");
        String email = request.getParameter("email");

        Medico medico = new Medico(id, crm, nome, senha, especialidade, email);
        dao.update(medico);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Medico medico = new Medico(id);
        dao.delete(medico);
        response.sendRedirect("lista");
    }
}