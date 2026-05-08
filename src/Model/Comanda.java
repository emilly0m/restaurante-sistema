package Model;

import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private int id;
    private int mesaId;
    private int garcomId;
    private List<ItemComanda> itens;

    public Comanda() {
        this.itens = new ArrayList<>();
    }

    public Comanda(int id, int mesaId, int garcomId) {
        this.id = id;
        this.mesaId = mesaId;
        this.garcomId = garcomId;
        this.itens = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMesaId() { return mesaId; }
    public void setMesaId(int mesaId) { this.mesaId = mesaId; }

    public int getGarcomId() { return garcomId; }
    public void setGarcomId(int garcomId) { this.garcomId = garcomId; }

    public List<ItemComanda> getItens() { return itens; }
    public void setItens(List<ItemComanda> itens) { this.itens = itens; }

    public void adicionarItem(ItemComanda item) {
        this.itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemComanda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
}