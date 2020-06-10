package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import view.administer.GerenciarContaCliente;
import view.client.ContaCliente;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneLogin;
	private JTextField textFieldNumeroConta;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/model/images/e-bank2.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 750, 315);

		contentPaneLogin = new JPanel();
		contentPaneLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneLogin);
		contentPaneLogin.setLayout(null);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNumero.setBounds(48, 120, 80, 23);
		contentPaneLogin.add(lblNumero);

		textFieldNumeroConta = new JTextField();
		textFieldNumeroConta.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldNumeroConta.setBounds(138, 115, 552, 32);
		contentPaneLogin.add(textFieldNumeroConta);
		textFieldNumeroConta.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSenha.setBounds(48, 167, 80, 24);
		contentPaneLogin.add(lblSenha);

		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(276, 16, 182, 86);
		contentPaneLogin.add(lblEbank);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					System.exit(0);
			}
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSair.setBounds(575, 215, 115, 44);
		contentPaneLogin.add(btnSair);

		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String passwordConvertidoString = new String(passwordFieldSenha.getPassword());
				if (textFieldNumeroConta.getText().equals("admin") && passwordConvertidoString.equals("admin")) {
					JOptionPane.showMessageDialog(null, "Login realizado como Administrador.", "Login de Administrador", JOptionPane.INFORMATION_MESSAGE);

					GerenciarContaCliente gerenciarContaCliente = new GerenciarContaCliente();
					gerenciarContaCliente.setVisible(true);
					gerenciarContaCliente.setLocationRelativeTo(null);
					setVisible(false);
				} else {
					// 89873721 Senha2 (inativo)
					// 473810 Senha3
					// 5409281 Senha4
					// 54111325 Senha6
					String nomeLogin = Operacoes.autentificarContaClienteLogin(textFieldNumeroConta.getText(), passwordConvertidoString);

					if (nomeLogin != null) {
						JOptionPane.showMessageDialog(null, "Login realizado.\nCliente: " + nomeLogin, "Login de Cliente", JOptionPane.INFORMATION_MESSAGE);

						ContaCliente contaCliente = new ContaCliente(textFieldNumeroConta.getText());
						contaCliente.setVisible(true);
						contaCliente.setLocationRelativeTo(null);
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Número da conta ou senha inválido(s).", "Erro ao Logar", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLogar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLogar.setBounds(445, 215, 115, 44);
		contentPaneLogin.add(btnLogar);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		passwordFieldSenha.setBounds(138, 163, 552, 32);
		contentPaneLogin.add(passwordFieldSenha);

		contentPaneLogin.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textFieldNumeroConta, passwordFieldSenha, btnLogar, btnSair }));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textFieldNumeroConta, passwordFieldSenha, btnLogar, btnSair }));

		// Evento ao clicar no X do Windows
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					System.exit(0);
			}
		});

		// Evento ao pressionar ESC
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					System.exit(0);
            }
        });

        // Definir botão padrão para a tecla ENTER
		JRootPane rootPane = SwingUtilities.getRootPane(btnLogar); 
		rootPane.setDefaultButton(btnLogar);
	}
}
