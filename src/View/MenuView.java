package View;

import Model.Garcom;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MenuView extends JFrame {

    private JPanel contentPane;

    public MenuView(Garcom garcom) {
        setTitle("Menu");
        setSize(400, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new java.awt.Color(255, 116, 108));
        setContentPane(contentPane);

        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setBounds(50, 30, 120, 30);
        btnProdutos.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnProdutos);

        JButton btnGarcons = new JButton("Garçons");
        btnGarcons.setBounds(200, 30, 120, 30);
        btnGarcons.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnGarcons);

        JButton btnMesas = new JButton("Mesas");
        btnMesas.setBounds(50, 80, 120, 30);
        btnMesas.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnMesas);

        JButton btnComandas = new JButton("Comandas");
        btnComandas.setBounds(200, 80, 120, 30);
        btnComandas.setBackground(java.awt.Color.WHITE);
        contentPane.add(btnComandas);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(125, 150, 120, 30);
        btnSair.setBackground(java.awt.Color.WHITE);
        btnSair.addActionListener(e -> System.exit(0));
        contentPane.add(btnSair);

        // ações dos botões
        btnProdutos.addActionListener(e -> {
            new ProdutoView().setVisible(true);
        });

        btnGarcons.addActionListener(e -> {
            new GarcomView().setVisible(true);
        });

        btnMesas.addActionListener(e -> {
            // descomente quando MesaView estiver pronta:
            // new MesaView().setVisible(true);
        });

        btnComandas.addActionListener(e -> {
            // descomente quando ComandaView estiver pronta:
            // new ComandaView().setVisible(true);
        });

        // esconde botões se não for admin
        if (!garcom.getPerfil().equals("admin")) {
            btnProdutos.setVisible(false);
            btnGarcons.setVisible(false);
            btnMesas.setVisible(false);
            btnComandas.setBounds(80, 80, 120, 30);
            btnSair.setBounds(220, 80, 120, 30);
        }
    }
}