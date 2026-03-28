package DAO;

import Model.Garcom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GarcomDAO {

    public Garcom autenticar(String login, String senha) {
        String sql = "SELECT * FROM garcom WHERE login = ? AND senha = ?";
        Connection conn = Conexao.conectar();
        Garcom garcom = null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                garcom = new Garcom();
                garcom.setId(rs.getInt("id"));
                garcom.setNome(rs.getString("nome"));
                garcom.setLogin(rs.getString("login"));
                garcom.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return garcom;
    }
}