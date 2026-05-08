package DAO;

import Model.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO {

    public boolean cadastrar(Mesa mesa) {
        String sql = "INSERT INTO mesa (status) VALUES (?)";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mesa.getStatus());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar mesa: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public boolean alterar(Mesa mesa) {
        String sql = "UPDATE mesa SET status = ? WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mesa.getStatus());
            stmt.setInt(2, mesa.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar mesa: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public List<Mesa> consultar() {
        String sql = "SELECT * FROM mesa";
        Connection conn = Conexao.conectar();
        List<Mesa> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mesa mesa = new Mesa();
                mesa.setId(rs.getInt("id"));
                mesa.setStatus(rs.getString("status"));
                lista.add(mesa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar mesas: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return lista;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM mesa WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir mesa: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }
}
