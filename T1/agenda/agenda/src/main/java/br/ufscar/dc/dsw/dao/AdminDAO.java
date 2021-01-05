package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

//import br.ufscar.dc.dsw.domain.Long;
//import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Admin;
//import br.ufscar.dc.dsw.domain.String;

public class AdminDAO extends GenericDAO {
/*
    public void insert(Admin admin) {

    	//Admin(id, login, senha)
    	
        String sql = "INSERT INTO Admin (login, senha) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getSenha());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

    public Admin get(Long id) {
        Admin admin = null;

        String sql = "SELECT * from Admin a where a.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");

                admin = new Admin(id, login, senha);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    public Admin getbyLogin(String login) {
        Admin admin = null;

        String sql = "SELECT * from Admin a WHERE a.login = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String senha = resultSet.getString("senha");
              
                admin = new Admin(id, login, senha);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}