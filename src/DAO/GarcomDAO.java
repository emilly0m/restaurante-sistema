package DAO;

import Model.Garcom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                garcom.setPerfil(rs.getString("perfil"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return garcom;
    }

    public boolean cadastrar(Garcom garcom) {
        String sql = "INSERT INTO garcom (nome, login, senha, perfil) VALUES (?, ?, ?, ?)";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, garcom.getNome());
            stmt.setString(2, garcom.getLogin());
            stmt.setString(3, garcom.getSenha());
            stmt.setString(4, garcom.getPerfil());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar garcom: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public boolean alterar(Garcom garcom) {
        String sql = "UPDATE garcom SET nome = ?, login = ?, senha = ?, perfil = ? WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, garcom.getNome());
            stmt.setString(2, garcom.getLogin());
            stmt.setString(3, garcom.getSenha());
            stmt.setString(4, garcom.getPerfil());
            stmt.setInt(5, garcom.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar garcom: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public List<Garcom> consultar() {
        String sql = "SELECT * FROM garcom";
        Connection conn = Conexao.conectar();
        List<Garcom> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Garcom garcom = new Garcom();
                garcom.setId(rs.getInt("id"));
                garcom.setNome(rs.getString("nome"));
                garcom.setLogin(rs.getString("login"));
                garcom.setSenha(rs.getString("senha"));
                garcom.setPerfil(rs.getString("perfil"));
                lista.add(garcom);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar garcons: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return lista;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM garcom WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir garcom: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }
}