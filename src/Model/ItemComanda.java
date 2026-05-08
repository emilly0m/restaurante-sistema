package Model;

public class ItemComanda {

    private int id;
    private int comandaId;
    private Produto produto;
    private int quantidade;
    private double subtotal;

    public ItemComanda() {}

    public ItemComanda(int id, int comandaId, Produto produto, int quantidade) {
        this.id = id;
        this.comandaId = comandaId;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = calcularSubtotal();
    }

    public double calcularSubtotal() {
        return this.produto.getPreco() * this.quantidade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getComandaId() { return comandaId; }
    public void setComandaId(int comandaId) { this.comandaId = comandaId; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
