package View;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.ProdutoController;
import Model.Produto;

public class ProdutoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ProdutoController controller;
    private int idSelecionado = -1;

    public ProdutoView() {
        controller = new ProdutoController();

        setTitle("Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new java.awt.Color(255, 116, 108));
        setContentPane(contentPane);

        // label e campo nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 15, 80, 25);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 15, 200, 25);
        contentPane.add(txtNome);

        // label e campo preco
        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 50, 80, 25);
        contentPane.add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(100, 50, 200, 25);
        contentPane.add(txtPreco);

        // botão cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(20, 90, 100, 25);
        btnCadastrar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnCadastrar);

        // botão alterar
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 90, 100, 25);
        btnAlterar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnAlterar);

        // botão excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 90, 100, 25);
        btnExcluir.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnExcluir);

        // botão voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(350, 90, 100, 25);
        btnVoltar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnVoltar);

        // tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Preço"}, 0);
        tabela = new JTable(modelo);
        tabela.setEnabled(true);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 130, 440, 270);
        contentPane.add(scroll);

        // carrega produtos na tabela ao abrir
        carregarTabela();

        // ação cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean sucesso = controller.cadastrar(txtNome.getText(), txtPreco.getText());
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    limparCampos();
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.");
                }
            }
        });

        // ação alterar
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na tabela.");
                    return;
                }
                boolean sucesso = controller.alterar(idSelecionado, txtNome.getText(), txtPreco.getText());
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
                    limparCampos();
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar produto.");
                }
            }
        });

        // ação excluir
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na tabela.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Deseja excluir este produto?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean sucesso = controller.excluir(idSelecionado);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                        limparCampos();
                        carregarTabela();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir produto.");
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

        // selecionar linha da tabela preenche os campos
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int linha = tabela.getSelectedRow();
                idSelecionado = (int) modelo.getValueAt(linha, 0);
                txtNome.setText(modelo.getValueAt(linha, 1).toString());
                txtPreco.setText(modelo.getValueAt(linha, 2).toString());
            }
        });
    }

    private void carregarTabela() {
        modelo.setRowCount(0); // limpa a tabela
        List<Produto> lista = controller.consultar();
        for (Produto p : lista) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco()});
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        idSelecionado = -1;
    }
}