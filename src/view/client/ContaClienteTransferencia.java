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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ContaClienteTransferencia extends JFrame {

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
					ContaClienteTransferencia frame = new ContaClienteTransferencia(null);
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
	public ContaClienteTransferencia(String numeroConta) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ContaClienteTransferencia.class.getResource("/model/images/e-bank2.png")));
		setTitle("Conta-Cliente Transfer\u00EAncia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 590);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setBounds(276, 16, 182, 86);
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		contentPane.add(lblEbank);

		JLabel lblTransferencia = new JLabel("TRANSFER\u00CANCIA");
		lblTransferencia.setFont(new Font("Arial", Font.BOLD, 30));
		lblTransferencia.setBounds(255, 281, 265, 40);
		contentPane.add(lblTransferencia);

		JLabel lblValorTransferencia = new JLabel("Valor:");
		lblValorTransferencia.setFont(new Font("Arial", Font.PLAIN, 20));
		lblValorTransferencia.setBounds(63, 389, 79, 24);
		contentPane.add(lblValorTransferencia);

		JLabel lblConta = new JLabel("Conta:");
		lblConta.setFont(new Font("Arial", Font.PLAIN, 20));
		lblConta.setBounds(63, 341, 79, 23);
		contentPane.add(lblConta);

		JFormattedTextField formattedTextFieldConta = new JFormattedTextField();
		formattedTextFieldConta.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldConta.setBounds(147, 337, 460, 32);
		contentPane.add(formattedTextFieldConta);

		JFormattedTextField formattedTextFieldValorTransferencia = new JFormattedTextField();
		formattedTextFieldValorTransferencia.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldValorTransferencia.setBounds(147, 385, 460, 32);
		contentPane.add(formattedTextFieldValorTransferencia);

		// Máscara de moeda
		DecimalFormat decimalFormatmatValor = new DecimalFormat("#,###,###.00");
		NumberFormatter numberFormatterValor = new NumberFormatter(decimalFormatmatValor);
		numberFormatterValor.setFormat(decimalFormatmatValor);
		numberFormatterValor.setAllowsInvalid(false);
		formattedTextFieldValorTransferencia.setFormatterFactory(new DefaultFormatterFactory(numberFormatterValor));

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

		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean transacao = Operacoes.realizarTransferencia(formattedTextFieldConta.getText(), numeroConta, formattedTextFieldValorTransferencia.getText().replace(",", "_").replace(".", "").replace("_", "."));

				if (transacao) {
					JOptionPane.showMessageDialog(null, "Transferência realizada.", "Realização de Transferência", JOptionPane.INFORMATION_MESSAGE);
					ContaCliente contaCliente = new ContaCliente(numeroConta);
					contaCliente.setVisible(true);
					contaCliente.setLocationRelativeTo(null);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao realizar transferência. Verifique a conta destino e/ou se há saldo.", "Erro ao Depositar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTransferir.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTransferir.setBounds(147, 433, 460, 44);
		contentPane.add(btnTransferir);

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
		btnCancelar.setBounds(147, 493, 460, 44);
		contentPane.add(btnCancelar);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{formattedTextFieldConta, formattedTextFieldValorTransferencia, btnTransferir, btnCancelar}));

		// Número da conta com apenas inteiro
		formattedTextFieldConta.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

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
		JRootPane rootPane = SwingUtilities.getRootPane(btnTransferir); 
		rootPane.setDefaultButton(btnTransferir);
	}
}
