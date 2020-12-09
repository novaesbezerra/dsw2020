package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Medico;

public class MedicoDAO extends GenericDAO {

    public void insert(Medico medico) {

        String sql = "INSERT INTO Medico (crm, nome, senha, especialidade, email) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, medico.getcrm());
            statement.setString(2, medico.getnome());
            statement.setString(3, medico.getsenha());
            statement.setString(4, medico.getespecialidade());
            statement.setString(5, medico.getemail());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medico> getAll() {

        List<Medico> listaMedicos = new ArrayList<>();

        String sql = "SELECT * from Medico"; 

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	Long id = resultSet.getLong("id");
                Long crm = resultSet.getLong("crm");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String especialidade = resultSet.getString("especialidade");
                String email = resultSet.getString("email");

                //Editora editora = new Editora(editora_id, cnpj, nome);
                Medico medico = new Medico(id, crm, nome, senha, especialidade, email);
                listaMedicos.add(medico);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }

    public void delete(Medico medico) {
        String sql = "DELETE FROM Medico where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, medico.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Medico medico) {
        String sql = "UPDATE Medico SET nome = ?, senha = ?, especialidade = ?";
        sql += ", email = ? WHERE crm = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, medico.getcrm());
            statement.setString(2, medico.getnome());
            statement.setString(3, medico.getsenha());
            statement.setString(4, medico.getespecialidade());
            statement.setString(5, medico.getemail());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Medico get(Long id) {
        Medico medico = null;

        String sql = "SELECT * from Medico where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long crm = resultSet.getLong("crm");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String especialidade = resultSet.getString("especialidade");
                String email = resultSet.getString("email");

                medico = new Medico(crm, nome, senha, especialidade, email);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }

    public Medico getbyEmail(String email) {
        Medico medico = null;

        String sql = "SELECT * from Medico m WHERE m.email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long crm = resultSet.getLong("crm");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String especialidade = resultSet.getString("especialidade");

                medico = new Medico(id, crm, nome, senha, especialidade, email);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }
}