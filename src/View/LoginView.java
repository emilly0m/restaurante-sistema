package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.LoginController;
import Model.Garcom;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new java.awt.Color(173, 216, 230));

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(120, 40, 80, 20);
        contentPane.add(lblUsuario);

        txtLogin = new JTextField();
        txtLogin.setBounds(120, 60, 200, 25);
        contentPane.add(txtLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(120, 95, 80, 20);
        contentPane.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 115, 200, 25);
        contentPane.add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(170, 160, 100, 25);
        btnEntrar.setBackground(java.awt.Color.WHITE);

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginController controller = new LoginController();
                Garcom garcom = controller.autenticar(
                        txtLogin.getText(),
                        new String(txtSenha.getPassword())
                );

                if (garcom != null) {
                    new MenuView(garcom).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
                }
            }
        });

        contentPane.add(btnEntrar);
    }
}