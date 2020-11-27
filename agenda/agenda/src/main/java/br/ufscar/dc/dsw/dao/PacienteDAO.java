package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Paciente;

public class PacienteDAO extends GenericDAO {

    public void insert(Paciente paciente) {

        String sql = "INSERT INTO Paciente (cpf, nome, sexo, senha, email, telefone, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, paciente.getcpf());
            statement.setString(2, paciente.getnome());
            statement.setInt(3, paciente.getsexo());
            statement.setString(4, paciente.getsenha());
            statement.setString(5, paciente.getemail());
            statement.setString(6, paciente.gettelefone());
            statement.setString(7, paciente.getnascimento());

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
                Long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                int sexo = resultSet.getInt("sexo");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String nascimento = resultSet.getString("nascimento");

                //Editora editora = new Editora(editora_id, cnpj, nome);
                Paciente paciente = new Paciente(cpf, nome, sexo, nascimento, senha);
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

            statement.setLong(1, paciente.getcpf());
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

            statement.setLong(1, paciente.getcpf());
            statement.setString(2, paciente.getnome());
            statement.setInt(3, paciente.getsexo());
            statement.setString(4, paciente.getsenha());
            statement.setString(5, paciente.getemail());
            statement.setString(6, paciente.gettelefone());
            statement.setString(7, paciente.getnascimento());
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
                String nome = resultSet.getString("nome");
                int sexo = resultSet.getInt("sexo");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String nascimento = resultSet.getString("nascimento");

                paciente = new Paciente(cpf, nome, sexo, nascimento, senha);
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