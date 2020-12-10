package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.util.Erro;


@WebServlet(urlPatterns = "/consultas/*")
public class ConsultaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ConsultaDAO dao;

    @Override
    public void init() {
        dao = new ConsultaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteLogado");
        Medico medico = (Medico) request.getSession().getAttribute("medicoLogado");
		Erro erros = new Erro();

		if (paciente == null && medico == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (paciente != null && paciente.getId().equals(0)) {
			erros.add("Acesso não autorizado!");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		} else if (medico != null) {
            System.out.println("Sou medico");
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
                case "/por_medico":
                    lista(request, response, "por_medico");
                    break;
                default:
                    lista(request, response, "por_paciente");
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response, String tipo) throws ServletException, IOException {
        if (tipo == "por_medico"){
            Medico medico = (Medico) request.getSession().getAttribute("medicoLogado");
            List<Consulta> listaConsultas = dao.getConsultaMedicos(medico);
            request.setAttribute("listaConsultas", listaConsultas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/consulta/lista.jsp");
            dispatcher.forward(request, response);
        } else {
            Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteLogado");
            List<Consulta> listaConsultas = dao.getAll(paciente);
            request.setAttribute("listaConsultas", listaConsultas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/consulta/lista.jsp");
            dispatcher.forward(request, response);
        }
    }

    private Map<Long, Medico> getMedicos() {
        Map<Long, Medico> medicos = new HashMap<>();
        for (Medico medico: new MedicoDAO().getAll()) {
            medicos.put(medico.getId(), medico);
        }
        return medicos;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("medicos", getMedicos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/consulta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("medico"));
        Float valor = Float.parseFloat("0");

        Medico medico = new MedicoDAO().get(id);
        Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteLogado");
        Long idPaciente = paciente.getId();

        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String minuto = request.getParameter("minuto");
        data = data + "/"+ hora + ":" + minuto;
        System.out.println(data);
        //String data = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        Consulta consulta = new Consulta(data, valor);
        consulta.setIdMedico(id);
        consulta.setIdPaciente(idPaciente);
        int duplicado = 0;
        duplicado = dao.getDuplicatos(idPaciente, id, data);
        if(duplicado == 0){
            dao.insert(consulta);
        }
        else{
            System.out.println("Erro");
        }
        response.sendRedirect("lista");
        
    }
}