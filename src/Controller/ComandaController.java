package Controller;

import DAO.ComandaDAO;
import DAO.MesaDAO;
import Model.Comanda;
import Model.ItemComanda;
import Model.Mesa;
import Model.Produto;
import java.util.List;

public class ComandaController {

    private ComandaDAO comandaDAO;
    private MesaDAO mesaDAO;

    public ComandaController() {
        this.comandaDAO = new ComandaDAO();
        this.mesaDAO = new MesaDAO();
    }

    public int abrirComanda(int mesaId, int garcomId) {
        if (mesaId <= 0 || garcomId <= 0) {
            System.out.println("Mesa ou garçom inválido.");
            return -1;
        }

        // abre a comanda
        Comanda comanda = new Comanda();
        comanda.setMesaId(mesaId);
        comanda.setGarcomId(garcomId);
        int comandaId = comandaDAO.abrirComanda(comanda);

        // muda status da mesa para ocupada
        if (comandaId != -1) {
            Mesa mesa = new Mesa();
            mesa.setId(mesaId);
            mesa.setStatus("ocupada");
            mesaDAO.alterar(mesa);
        }

        return comandaId;
    }

    public boolean adicionarItem(int comandaId, Produto produto, int quantidade) {
        if (comandaId <= 0 || produto == null || quantidade <= 0) {
            System.out.println("Dados inválidos para adicionar item.");
            return false;
        }

        ItemComanda item = new ItemComanda();
        item.setComandaId(comandaId);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setSubtotal(produto.getPreco() * quantidade);
        return comandaDAO.adicionarItem(item);
    }

    public double calcularTotal(int comandaId) {
        List<ItemComanda> itens = comandaDAO.consultarItens(comandaId);
        double total = 0;
        for (ItemComanda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<ItemComanda> consultarItens(int comandaId) {
        return comandaDAO.consultarItens(comandaId);
    }

    public boolean fecharComanda(int comandaId, int mesaId) {
        if (comandaId <= 0 || mesaId <= 0) {
            System.out.println("ID inválido.");
            return false;
        }

        boolean fechou = comandaDAO.fecharComanda(comandaId);

        // muda status da mesa para livre
        if (fechou) {
            Mesa mesa = new Mesa();
            mesa.setId(mesaId);
            mesa.setStatus("livre");
            mesaDAO.alterar(mesa);
        }

        return fechou;
    }
}
