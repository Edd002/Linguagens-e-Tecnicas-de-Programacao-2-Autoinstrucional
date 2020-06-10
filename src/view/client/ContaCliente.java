package view.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import view.Login;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ContaCliente extends JFrame {

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
					ContaCliente frame = new ContaCliente(null);
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
	public ContaCliente(String numeroConta) {
		setResizable(false);
		setTitle("Conta-Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ContaCliente.class.getResource("/model/images/e-bank2.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 610);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(276, 16, 182, 86);
		contentPane.add(lblEbank);

		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaClienteDeposito contaClienteDeposito = new ContaClienteDeposito(numeroConta);
				contaClienteDeposito.setVisible(true);
				contaClienteDeposito.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnDepositar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDepositar.setBounds(96, 290, 555, 44);
		contentPane.add(btnDepositar);

		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaClienteSaque contaClienteSaque = new ContaClienteSaque(numeroConta);
				contaClienteSaque.setVisible(true);
				contaClienteSaque.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnSacar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSacar.setBounds(96, 345, 555, 44);
		contentPane.add(btnSacar);

		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaClienteTransferencia contaClienteTransferencia = new ContaClienteTransferencia(numeroConta);
				contaClienteTransferencia.setVisible(true);
				contaClienteTransferencia.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnTransferir.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTransferir.setBounds(96, 400, 555, 44);
		contentPane.add(btnTransferir);

		JButton btnGerarExtrato = new JButton("Gerar Extrato");
		btnGerarExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarExtrato gerarExtrato = new GerarExtrato(numeroConta);
				gerarExtrato.setVisible(true);
				gerarExtrato.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnGerarExtrato.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGerarExtrato.setBounds(96, 455, 555, 44);
		contentPane.add(btnGerarExtrato);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(96, 510, 555, 44);
		contentPane.add(btnCancelar);

		JLabel lblCliente = new JLabel("Cliente: " + Operacoes.buscarNomeClientePorNumeroConta(numeroConta));
		lblCliente.setFont(new Font("Arial", Font.PLAIN, 30));
		lblCliente.setBounds(147, 113, 450, 40);
		contentPane.add(lblCliente);

		JLabel lblInformacoesConta = new JLabel("Conta: " + Operacoes.buscarInformacoesClientePorNumeroConta(numeroConta));
		lblInformacoesConta.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInformacoesConta.setBounds(147, 164, 450, 40);
		contentPane.add(lblInformacoesConta);

		JLabel lblSaldo = new JLabel("Saldo: " + String.format("R$%.2f", Operacoes.buscarSaldoClientePorNumeroConta(numeroConta)));
		lblSaldo.setFont(new Font("Arial", Font.PLAIN, 30));
		lblSaldo.setBounds(147, 215, 450, 40);
		contentPane.add(lblSaldo);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnDepositar, btnSacar, btnTransferir, btnGerarExtrato, btnCancelar}));

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
