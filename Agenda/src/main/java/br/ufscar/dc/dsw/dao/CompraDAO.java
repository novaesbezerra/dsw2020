package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;

public class ConsultaDAO extends GenericDAO {

    public void insert(Consulta consulta) {

        String sql = "INSERT INTO Consulta (data, valor, medico_id, paciente_id) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, consulta.getData());
            statement.setFloat(2, consulta.getValor());
            statement.setLong(3, consulta.getMedico().getId());
            statement.setLong(4, consulta.getPaciente().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll(Paciente paciente) {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * from Consulta c where c.USUARIO_ID = ? order by c.ID";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, paciente.getId());
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String data = resultSet.getString("data");
                Float valor = resultSet.getFloat("valor");
                Long medicoId = resultSet.getLong("medico_id");
                Medico medico = new MedicoDAO().get(medicoId);            
                Consulta consulta = new Consulta(id, data, valor, medico, paciente);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
}