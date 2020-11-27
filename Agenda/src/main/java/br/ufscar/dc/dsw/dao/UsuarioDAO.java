package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Paciente;

public class PacienteDAO extends GenericDAO {

    public void insert(Paciente paciente) {

        String sql = "INSERT INTO Paciente (nome, login, senha, papel) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getLogin());
            statement.setString(3, paciente.getSenha());
            statement.setString(4, paciente.getPapel());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> getAll() {

        List<Paciente> listaPacientes = new ArrayList<>();

        String sql = "SELECT * from Paciente u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Paciente paciente = new Paciente(id, nome, login, senha, papel);
                listaPacientes.add(paciente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    public void delete(Paciente paciente) {
        String sql = "DELETE FROM Paciente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, paciente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Paciente paciente) {
        String sql = "UPDATE Paciente SET nome = ?, login = ?, senha = ?, papel = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getLogin());
            statement.setString(3, paciente.getSenha());
            statement.setString(4, paciente.getPapel());
            statement.setLong(5, paciente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente get(Long id) {
        Paciente paciente = null;

        String sql = "SELECT * from Paciente WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                paciente = new Paciente(id, nome, login, senha, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
    
    public Paciente getbyLogin(String login) {
        Paciente paciente = null;

        String sql = "SELECT * from Paciente WHERE login = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                paciente = new Paciente(id, nome, login, senha, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
    
}