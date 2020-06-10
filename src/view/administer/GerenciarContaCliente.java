package view.administer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import view.Login;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GerenciarContaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarContaCliente frame = new GerenciarContaCliente();
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
	public GerenciarContaCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GerenciarContaCliente.class.getResource("/model/images/e-bank2.png")));
		setTitle("Gerenciar Conta-Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 475);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrarContaCliente = new JButton("Cadastrar Conta-Cliente");
		btnCadastrarContaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarContaCliente cadastrarContaCliente = new CadastrarContaCliente();
				cadastrarContaCliente.setVisible(true);
				cadastrarContaCliente.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnCadastrarContaCliente.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCadastrarContaCliente.setBounds(96, 130, 555, 44);
		contentPane.add(btnCadastrarContaCliente);
		
		JButton btnAlterarContaCliente = new JButton("Alterar Conta-Cliente");
		btnAlterarContaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterarContaCliente alterarContaCliente = new AlterarContaCliente();
				alterarContaCliente.setVisible(true);
				alterarContaCliente.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnAlterarContaCliente.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAlterarContaCliente.setBounds(96, 190, 555, 44);
		contentPane.add(btnAlterarContaCliente);
		
		JButton btnGerarRelatrios = new JButton("Gerar Relat\u00F3rios");
		btnGerarRelatrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarRelatorios gerarRelatorios = new GerarRelatorios();
				gerarRelatorios.setVisible(true);
				gerarRelatorios.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnGerarRelatrios.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGerarRelatrios.setBounds(96, 310, 555, 44);
		contentPane.add(btnGerarRelatrios);
		
		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(276, 16, 182, 86);
		contentPane.add(lblEbank);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(96, 370, 555, 44);
		contentPane.add(btnCancelar);

		JButton btnDesativarContaCliente = new JButton("Desativar Conta-Cliente");
		btnDesativarContaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesativarContaCliente desativarContaCliente = new DesativarContaCliente();
				desativarContaCliente.setVisible(true);
				desativarContaCliente.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnDesativarContaCliente.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDesativarContaCliente.setBounds(96, 250, 555, 44);
		contentPane.add(btnDesativarContaCliente);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnCadastrarContaCliente, btnAlterarContaCliente, btnDesativarContaCliente, btnGerarRelatrios, btnCancelar}));

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
            	Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
            }
        });

        // Evento ao pressionar ENTER
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "clickButton");
		getRootPane().getActionMap().put("clickButton", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// Se o foco estiver em cima de uma botão
				if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner() instanceof JButton) {
					JButton jButton = (JButton) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
					jButton.doClick();
				}
			}
		});
	}
}
