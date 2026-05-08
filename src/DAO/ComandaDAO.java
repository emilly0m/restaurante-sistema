package DAO;

import Model.Comanda;
import Model.ItemComanda;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComandaDAO {

    public int abrirComanda(Comanda comanda) {
        String sql = "INSERT INTO comanda (mesa_id, garcom_id) VALUES (?, ?)";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, comanda.getMesaId());
            stmt.setInt(2, comanda.getGarcomId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retorna o id da comanda criada
            }
        } catch (SQLException e) {
            System.out.println("Erro ao abrir comanda: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return -1; // retorna -1 se falhou
    }

    public boolean adicionarItem(ItemComanda item) {
        String sql = "INSERT INTO item_comanda (comanda_id, produto_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";
        Connection conn = Conexao.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, item.getComandaId());
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getSubtotal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }

    public List<ItemComanda> consultarItens(int comandaId) {
        String sql = "SELECT ic.*, p.nome, p.preco FROM item_comanda ic " +
                "JOIN produto p ON ic.produto_id = p.id " +
                "WHERE ic.comanda_id = ?";
        Connection conn = Conexao.conectar();
        List<ItemComanda> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, comandaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("produto_id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));

                ItemComanda item = new ItemComanda();
                item.setId(rs.getInt("id"));
                item.setComandaId(comandaId);
                item.setProduto(produto);
                item.setQuantidade(rs.getInt("quantidade"));
                item.setSubtotal(rs.getDouble("subtotal"));
                lista.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar itens: " + e.getMessage());
        } finally {
            Conexao.desconectar(conn);
        }

        return lista;
    }

    public boolean fecharComanda(int comandaId) {
        String sql = "DELETE FROM comanda WHERE id = ?";
        Connection conn = Conexao.conectar();

        try {
            // primeiro apaga os itens
            PreparedStatement stmtItens = conn.prepareStatement(
                    "DELETE FROM item_comanda WHERE comanda_id = ?"
            );
            stmtItens.setInt(1, comandaId);
            stmtItens.executeUpdate();

            // depois apaga a comanda
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, comandaId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao fechar comanda: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn);
        }
    }
}