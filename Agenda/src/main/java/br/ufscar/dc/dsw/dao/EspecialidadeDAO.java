package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Especialidade;

public class EspecialidadeDAO extends GenericDAO {

    public void insert(Especialidade especialidade) {

        String sql = "INSERT INTO Especialidade (cnpj, nome) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, especialidade.getCNPJ());
            statement.setString(2, especialidade.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Especialidade> getAll() {

        List<Especialidade> listaEspecialidades = new ArrayList<>();

        String sql = "SELECT * from Especialidade";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Especialidade especialidade = new Especialidade(id, cnpj, nome);
                especialidade.setQtdeMedicos(new MedicoDAO().countByEspecialidade(id));
                listaEspecialidades.add(especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEspecialidades;
    }

    public void delete(Especialidade especialidade) {
        String sql = "DELETE FROM Especialidade where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, especialidade.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Especialidade especialidade) {
        String sql = "UPDATE Especialidade SET cnpj = ?, nome = ?";
        sql += " WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, especialidade.getCNPJ());
            statement.setString(2, especialidade.getNome());
            statement.setLong(3, especialidade.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Especialidade get(Long id) {
        Especialidade especialidade = null;
        
        String sql = "SELECT * from Especialidade where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                especialidade = new Especialidade(id, cnpj, nome);
                especialidade.setQtdeMedicos(new MedicoDAO().countByEspecialidade(id));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return especialidade;
    }
}