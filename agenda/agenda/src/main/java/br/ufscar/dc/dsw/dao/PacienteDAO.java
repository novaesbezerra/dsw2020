package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import br.ufscar.dc.dsw.domain.Long;
//import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Paciente;
//import br.ufscar.dc.dsw.domain.String;

public class PacienteDAO extends GenericDAO {

    public void insert(Paciente paciente) {

    	//Paciente(nome, email, senha, cpf, telefone, sexo, nascimento)
    	
        String sql = "INSERT INTO Paciente (id, nome, email, senha, cpf, telefone, sexo, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setString(2, paciente.getNome());
            statement.setString(5, paciente.getEmail());
            statement.setString(4, paciente.getSenha());
            statement.setLong(4, paciente.getCpf());
            statement.setString(6, paciente.getTelefone());
            statement.setString(3, paciente.getSexo());
            statement.setString(7, paciente.getNascimento());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> getAll() {

        List<Paciente> listaPacientes = new ArrayList<>();

        String sql = "SELECT * from Paciente"; //p, Editora e where l.EDITORA_ID = e.ID order by l.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Long cpf = resultSet.getLong("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");           
                String nascimento = resultSet.getString("nascimento");

                Paciente paciente = new Paciente(id, nome, email, senha, cpf, telefone, sexo, nascimento);
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
        String sql = "DELETE FROM Paciente where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, paciente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Paciente paciente) {
        String sql = "UPDATE Paciente SET nome = ?, sexo = ?, nascimento = ?";
        sql += ", senha = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, paciente.getCpf());
            statement.setString(2, paciente.getNome());
            statement.setString(3, paciente.getSexo());
            statement.setString(4, paciente.getSenha());
            statement.setString(5, paciente.getEmail());
            statement.setString(6, paciente.getTelefone());
            statement.setString(7, paciente.getNascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente get(Long cpf) {
        Paciente paciente = null;

        String sql = "SELECT * from Paciente p where p.cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String nascimento = resultSet.getString("nascimento");

                paciente = new Paciente(id, nome, email, senha, cpf, telefone, sexo, nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }

    public Paciente getbyEmail(String email) {
        Paciente paciente = null;

        String sql = "SELECT * from Paciente p WHERE p.email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String nascimento = resultSet.getString("nascimento");

                paciente = new Paciente(id, nome, email, senha, cpf, telefone, sexo, nascimento);
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