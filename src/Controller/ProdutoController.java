package Controller;

import DAO.ProdutoDAO;
import Model.Produto;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public boolean cadastrar(String nome, String preco) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do produto não pode ser vazio.");
            return false;
        }
        try {
            double precoDouble = Double.parseDouble(preco.replace(",", "."));
            if (precoDouble <= 0) {
                System.out.println("Preço deve ser maior que zero.");
                return false;
            }
            Produto produto = new Produto();
            produto.setNome(nome.trim());
            produto.setPreco(precoDouble);
            return produtoDAO.cadastrar(produto);
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido.");
            return false;
        }
    }

    public boolean alterar(int id, String nome, String preco) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do produto não pode ser vazio.");
            return false;
        }
        try {
            double precoDouble = Double.parseDouble(preco.replace(",", "."));
            if (precoDouble <= 0) {
                System.out.println("Preço deve ser maior que zero.");
                return false;
            }
            Produto produto = new Produto();
            produto.setId(id);
            produto.setNome(nome.trim());
            produto.setPreco(precoDouble);
            return produtoDAO.alterar(produto);
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido.");
            return false;
        }
    }
}
