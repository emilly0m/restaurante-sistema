package View;

import Model.Mesa;
import Controller.MesaController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
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

public class MesaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboStatus;
    private JTable tabela;
    private DefaultTableModel modelo;
    private MesaController controller;
    private int idSelecionado = -1;

    public MesaView() {
        controller = new MesaController();

        setTitle("Mesas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new java.awt.Color(255, 116, 108));
        setContentPane(contentPane);

        // status
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 15, 80, 25);
        contentPane.add(lblStatus);

        comboStatus = new JComboBox<>(new String[]{"livre", "ocupada"});
        comboStatus.setBounds(100, 15, 200, 25);
        contentPane.add(comboStatus);

        // botões
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(20, 55, 100, 25);
        btnCadastrar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnCadastrar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 55, 100, 25);
        btnAlterar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 55, 100, 25);
        btnExcluir.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(350, 55, 100, 25);
        btnVoltar.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnVoltar);

        // tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Status"}, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 95, 440, 255);
        contentPane.add(scroll);

        carregarTabela();

        // ação cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean sucesso = controller.cadastrar();
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Mesa cadastrada com sucesso!");
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar mesa.");
                }
            }
        });

        // ação alterar
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa na tabela.");
                    return;
                }
                boolean sucesso = controller.alterar(
                        idSelecionado,
                        comboStatus.getSelectedItem().toString()
                );
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Mesa alterada com sucesso!");
                    idSelecionado = -1;
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar mesa.");
                }
            }
        });

        // ação excluir
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa na tabela.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Deseja excluir esta mesa?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean sucesso = controller.excluir(idSelecionado);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Mesa excluída com sucesso!");
                        idSelecionado = -1;
                        carregarTabela();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir mesa.");
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

        // selecionar linha da tabela preenche o combo
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int linha = tabela.getSelectedRow();
                idSelecionado = (int) modelo.getValueAt(linha, 0);
                comboStatus.setSelectedItem(modelo.getValueAt(linha, 1).toString());
            }
        });
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Mesa> lista = controller.consultar();
        for (Mesa m : lista) {
            modelo.addRow(new Object[]{m.getId(), m.getStatus()});
        }
    }
}