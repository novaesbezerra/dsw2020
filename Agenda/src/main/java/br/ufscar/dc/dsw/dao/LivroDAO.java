package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Especialidade;
import br.ufscar.dc.dsw.domain.Medico;

public class MedicoDAO extends GenericDAO {

    public void insert(Medico medico) {

        String sql = "INSERT INTO Medico (titulo, autor, ano, preco, especialidade_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, medico.getTitulo());
            statement.setString(2, medico.getAutor());
            statement.setInt(3, medico.getAno());
            statement.setFloat(4, medico.getPreco());
            statement.setLong(5, medico.getEspecialidade().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medico> getAll() {

        List<Medico> listaMedicos = new ArrayList<>();

        String sql = "SELECT * from Medico l, Especialidade e where l.EDITORA_ID = e.ID order by l.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");
                Long especialidade_id = resultSet.getLong(6);
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Especialidade especialidade = new Especialidade(especialidade_id, cnpj, nome);
                Medico medico = new Medico(id, titulo, autor, ano, preco, especialidade);
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
        String sql = "UPDATE Medico SET titulo = ?, autor = ?, ano = ?, preco = ?";
        sql += ", especialidade_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, medico.getTitulo());
            statement.setString(2, medico.getAutor());
            statement.setInt(3, medico.getAno());
            statement.setFloat(4, medico.getPreco());
            statement.setFloat(5, medico.getEspecialidade().getId());
            statement.setLong(6, medico.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Medico get(Long id) {
        Medico medico = null;

        String sql = "SELECT * from Medico l, Especialidade e where l.id = ? and l.EDITORA_ID = e.ID";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");

                Long especialidadeID = resultSet.getLong("especialidade_id");
                Especialidade especialidade = new EspecialidadeDAO().get(especialidadeID);

                medico = new Medico(id, titulo, autor, ano, preco, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }

    public int countByEspecialidade(Long id) {
        int contador = 0;
        
        String sql = "SELECT count(*) from Medico l where l.EDITORA_ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contador = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }
}