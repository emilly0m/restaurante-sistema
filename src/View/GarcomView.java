package View;

import Model.Garcom;
import Controller.GarcomController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class GarcomView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtLogin;
    private JTextField txtSenha;
    private JComboBox<String> comboPerfil;
    private JTable tabela;
    private DefaultTableModel modelo;
    private GarcomController controller;
    private int idSelecionado = -1;

    public GarcomView() {
        controller = new GarcomController();

        setTitle("Garçons");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new java.awt.Color(255, 116, 108));
        setContentPane(contentPane);

        // nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 15, 80, 25);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 15, 200, 25);
        contentPane.add(txtNome);

        // login
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(20, 50, 80, 25);
        contentPane.add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(100, 50, 200, 25);
        contentPane.add(txtLogin);

        // senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 85, 80, 25);
        contentPane.add(lblSenha);

        txtSenha = new JTextField();
        txtSenha.setBounds(100, 85, 200, 25);
        contentPane.add(txtSenha);

        // botões
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(20, 120, 100, 25);
        btnCadastrar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnCadastrar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 120, 100, 25);
        btnAlterar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 120, 100, 25);
        btnExcluir.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(350, 120, 100, 25);
        btnVoltar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnVoltar);

        // tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Login", "Perfil"}, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 160, 440, 280);
        contentPane.add(scroll);

        carregarTabela();

        // ação cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean sucesso = controller.cadastrar(
                        txtNome.getText(),
                        txtLogin.getText(),
                        txtSenha.getText(),
                        "garcom"
                );
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Garçom cadastrado com sucesso!");
                    limparCampos();
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar garçom.");
                }
            }
        });

        // ação alterar
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um garçom na tabela.");
                    return;
                }
                boolean sucesso = controller.alterar(
                        idSelecionado,
                        txtNome.getText(),
                        txtLogin.getText(),
                        txtSenha.getText(),
                        "garcom"
                );
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Garçom alterado com sucesso!");
                    limparCampos();
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar garçom.");
                }
            }
        });

        // ação excluir
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um garçom na tabela.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Deseja excluir este garçom?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean sucesso = controller.excluir(idSelecionado);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Garçom excluído com sucesso!");
                        limparCampos();
                        carregarTabela();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir garçom.");
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
                txtLogin.setText(modelo.getValueAt(linha, 2).toString());
                txtSenha.setText("");
                comboPerfil.setSelectedItem(modelo.getValueAt(linha, 3).toString());
            }
        });
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Garcom> lista = controller.consultarGarcons();
        for (Garcom g : lista) {
            modelo.addRow(new Object[]{g.getId(), g.getNome(), g.getLogin(), g.getPerfil()});
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        comboPerfil.setSelectedIndex(0);
        idSelecionado = -1;
    }
}