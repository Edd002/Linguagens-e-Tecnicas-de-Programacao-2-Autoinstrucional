package view.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ContaClienteDeposito extends JFrame {

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
					ContaClienteDeposito frame = new ContaClienteDeposito(null);
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
	public ContaClienteDeposito(String numeroConta) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ContaClienteDeposito.class.getResource("/model/images/e-bank2.png")));
		setTitle("Conta-Cliente Dep\u00F3sito");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 540);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setBounds(276, 16, 182, 86);
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		contentPane.add(lblEbank);

		JLabel lblDeposito = new JLabel("DEP\u00D3SITO");
		lblDeposito.setFont(new Font("Arial", Font.BOLD, 30));
		lblDeposito.setBounds(295, 281, 170, 40);
		contentPane.add(lblDeposito);

		JLabel lblValorDeposito = new JLabel("Valor:");
		lblValorDeposito.setFont(new Font("Arial", Font.PLAIN, 20));
		lblValorDeposito.setBounds(63, 341, 79, 24);
		contentPane.add(lblValorDeposito);

		JFormattedTextField formattedTextFieldValorDeposito = new JFormattedTextField();
		formattedTextFieldValorDeposito.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldValorDeposito.setBounds(147, 337, 460, 32);
		contentPane.add(formattedTextFieldValorDeposito);

		// Máscara de moeda
		DecimalFormat decimalFormatmatValor = new DecimalFormat("#,###,###.00");
		NumberFormatter numberFormatterValor = new NumberFormatter(decimalFormatmatValor);
		numberFormatterValor.setFormat(decimalFormatmatValor);
		numberFormatterValor.setAllowsInvalid(false);
		formattedTextFieldValorDeposito.setFormatterFactory(new DefaultFormatterFactory(numberFormatterValor));

		JLabel lblCliente = new JLabel("Cliente: " + Operacoes.buscarNomeClientePorNumeroConta(numeroConta));
		lblCliente.setFont(new Font("Arial", Font.PLAIN, 30));
		lblCliente.setBounds(147, 113, 450, 40);
		contentPane.add(lblCliente);

		JLabel lblInformarcoesConta = new JLabel("Conta: " + Operacoes.buscarInformacoesClientePorNumeroConta(numeroConta));
		lblInformarcoesConta.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInformarcoesConta.setBounds(147, 164, 450, 40);
		contentPane.add(lblInformarcoesConta);

		JLabel lblSaldo = new JLabel("Saldo: " + String.format("R$%.2f", Operacoes.buscarSaldoClientePorNumeroConta(numeroConta)));
		lblSaldo.setFont(new Font("Arial", Font.PLAIN, 30));
		lblSaldo.setBounds(147, 215, 450, 40);
		contentPane.add(lblSaldo);

		JButton btnDepositar = new JButton("Dep\u00F3sitar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean transacao = Operacoes.realizarDeposito(numeroConta, formattedTextFieldValorDeposito.getText().replace(",", "_").replace(".", "").replace("_", "."));

				if (transacao) {
					JOptionPane.showMessageDialog(null, "Depósito realizado.", "Realização de Depósito", JOptionPane.INFORMATION_MESSAGE);
					ContaCliente contaCliente = new ContaCliente(numeroConta);
					contaCliente.setVisible(true);
					contaCliente.setLocationRelativeTo(null);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao realizar depósito.", "Erro ao Depositar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDepositar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDepositar.setBounds(147, 385, 460, 44);
		contentPane.add(btnDepositar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaCliente contaCliente = new ContaCliente(numeroConta);
				contaCliente.setVisible(true);
				contaCliente.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(147, 445, 460, 44);
		contentPane.add(btnCancelar);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{formattedTextFieldValorDeposito, btnDepositar, btnCancelar}));

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
            	ContaCliente contaCliente = new ContaCliente(numeroConta);
				contaCliente.setVisible(true);
				contaCliente.setLocationRelativeTo(null);
				setVisible(false);
            }
        });

        // Definir botão padrão para a tecla ENTER
		JRootPane rootPane = SwingUtilities.getRootPane(btnDepositar); 
		rootPane.setDefaultButton(btnDepositar);
	}
}
