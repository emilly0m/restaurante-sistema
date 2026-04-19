package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean cadastrar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar produto: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public List<Produto> consultar() {
        String sql = "SELECT * FROM produto";
        Connection conn = Conexao.conectar();
        List<Produto> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                lista.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return lista;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }
}