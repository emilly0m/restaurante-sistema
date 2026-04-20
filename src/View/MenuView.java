package View;

import Model.Garcom;
import javax.swing.JFrame;
import javax.swing.JButton;

public class MenuView extends JFrame {

    public MenuView(Garcom garcom) {
        setTitle("Menu");
        setSize(400, 300);
        setLayout(null);

        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setBounds(50, 30, 120, 30);
        add(btnProdutos);

        JButton btnGarcons = new JButton("Garçons");
        btnGarcons.setBounds(200, 30, 120, 30);
        add(btnGarcons);

        JButton btnMesas = new JButton("Mesas");
        btnMesas.setBounds(50, 80, 120, 30);
        add(btnMesas);

        JButton btnComandas = new JButton("Comandas");
        btnComandas.setBounds(200, 80, 120, 30);
        add(btnComandas);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(125, 150, 120, 30);
        btnSair.addActionListener(e -> System.exit(0));
        add(btnSair);

        // esconde botões se não for admin
        if (!garcom.getPerfil().equals("admin")) {
            btnProdutos.setVisible(false);
            btnGarcons.setVisible(false);
            btnMesas.setVisible(false);
        }
    }
}