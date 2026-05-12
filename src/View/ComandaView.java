package View;

import Model.Garcom;
import Model.ItemComanda;
import Model.Mesa;
import Model.Produto;
import Controller.ComandaController;
import Controller.MesaController;
import Controller.ProdutoController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ComandaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboMesa;
    private JComboBox<String> comboProduto;
    private JTextField txtQuantidade;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ComandaController comandaController;
    private MesaController mesaController;
    private ProdutoController produtoController;
    private List<Mesa> mesas;
    private List<Produto> produtos;
    private Garcom garcom;
    private int comandaId = -1;

    public ComandaView(Garcom garcom) {
        this.garcom = garcom;
        comandaController = new ComandaController();
        mesaController = new MesaController();
        produtoController = new ProdutoController();

        setTitle("Comanda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 550);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new java.awt.Color(255, 116, 108));
        setContentPane(contentPane);

        // combo mesa
        JLabel lblMesa = new JLabel("Mesa:");
        lblMesa.setBounds(20, 15, 80, 25);
        contentPane.add(lblMesa);

        comboMesa = new JComboBox<>();
        comboMesa.setBounds(100, 15, 200, 25);
        contentPane.add(comboMesa);
        carregarMesas();

        // combo produto
        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setBounds(20, 50, 80, 25);
        contentPane.add(lblProduto);

        comboProduto = new JComboBox<>();
        comboProduto.setBounds(100, 50, 200, 25);
        contentPane.add(comboProduto);
        carregarProdutos();

        // quantidade
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(20, 85, 90, 25);
        contentPane.add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(120, 85, 80, 25);
        contentPane.add(txtQuantidade);

        // botões
        JButton btnAbrirComanda = new JButton("Abrir Comanda");
        btnAbrirComanda.setBounds(20, 125, 130, 25);
        btnAbrirComanda.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnAbrirComanda);

        JButton btnAdicionarItem = new JButton("Adicionar Item");
        btnAdicionarItem.setBounds(160, 125, 130, 25);
        btnAdicionarItem.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnAdicionarItem);

        JButton btnCalcularTotal = new JButton("Calcular Total");
        btnCalcularTotal.setBounds(20, 160, 130, 25);
        btnCalcularTotal.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnCalcularTotal);

        JButton btnFecharComanda = new JButton("Fechar Comanda");
        btnFecharComanda.setBounds(160, 160, 130, 25);
        btnFecharComanda.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnFecharComanda);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(350, 160, 100, 25);
        btnVoltar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnVoltar);

        // tabela de itens
        modelo = new DefaultTableModel(new String[]{"Produto", "Quantidade", "Subtotal"}, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 200, 440, 290);
        contentPane.add(scroll);

        // ação abrir comanda
        btnAbrirComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comandaId != -1) {
                    JOptionPane.showMessageDialog(null, "Já existe uma comanda aberta!");
                    return;
                }
                int indiceMesa = comboMesa.getSelectedIndex();
                if (indiceMesa == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa.");
                    return;
                }
                Mesa mesa = mesas.get(indiceMesa);
                comandaId = comandaController.abrirComanda(mesa.getId(), garcom.getId());
                if (comandaId != -1) {
                    JOptionPane.showMessageDialog(null, "Comanda aberta! ID: " + comandaId);
                    carregarMesas(); // atualiza status das mesas
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir comanda.");
                }
            }
        });

        // ação adicionar item
        btnAdicionarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comandaId == -1) {
                    JOptionPane.showMessageDialog(null, "Abra uma comanda primeiro.");
                    return;
                }
                int indiceProduto = comboProduto.getSelectedIndex();
                if (indiceProduto == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto.");
                    return;
                }
                try {
                    int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
                    if (quantidade <= 0) {
                        JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que zero.");
                        return;
                    }
                    Produto produto = produtos.get(indiceProduto);
                    boolean sucesso = comandaController.adicionarItem(comandaId, produto, quantidade);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Item adicionado!");
                        txtQuantidade.setText("");
                        carregarItens();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao adicionar item.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida.");
                }
            }
        });

        // ação calcular total
        btnCalcularTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comandaId == -1) {
                    JOptionPane.showMessageDialog(null, "Nenhuma comanda aberta.");
                    return;
                }
                double total = comandaController.calcularTotal(comandaId);
                JOptionPane.showMessageDialog(null, "Total da comanda: R$ " + String.format("%.2f", total));
            }
        });

        // ação fechar comanda
        btnFecharComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comandaId == -1) {
                    JOptionPane.showMessageDialog(null, "Nenhuma comanda aberta.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Deseja fechar a comanda?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int indiceMesa = comboMesa.getSelectedIndex();
                    Mesa mesa = mesas.get(indiceMesa);
                    boolean sucesso = comandaController.fecharComanda(comandaId, mesa.getId());
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Comanda fechada com sucesso!");
                        comandaId = -1;
                        modelo.setRowCount(0);
                        carregarMesas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao fechar comanda.");
                    }
                }
            }
        });

        // ação voltar
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void carregarMesas() {
        comboMesa.removeAllItems();
        mesas = mesaController.consultar();
        for (Mesa m : mesas) {
            comboMesa.addItem("Mesa " + m.getId() + " - " + m.getStatus());
        }
    }

    private void carregarProdutos() {
        comboProduto.removeAllItems();
        produtos = produtoController.consultar();
        for (Produto p : produtos) {
            comboProduto.addItem(p.getNome() + " - R$ " + String.format("%.2f", p.getPreco()));
        }
    }

    private void carregarItens() {
        modelo.setRowCount(0);
        List<ItemComanda> itens = comandaController.consultarItens(comandaId);
        for (ItemComanda item : itens) {
            modelo.addRow(new Object[]{
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    String.format("R$ %.2f", item.getSubtotal())
            });
        }
    }
}