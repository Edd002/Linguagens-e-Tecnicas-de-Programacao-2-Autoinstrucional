package view.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import controller.structure.Transacao;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class GerarExtrato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneGerarExtrato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarExtrato frame = new GerarExtrato(null);
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
	public GerarExtrato(String numeroConta) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GerarExtrato.class.getResource("/model/images/e-bank2.png")));
		setResizable(false);
		setTitle("Extrato");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 720);

		contentPaneGerarExtrato = new JPanel();
		contentPaneGerarExtrato.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneGerarExtrato);
		contentPaneGerarExtrato.setLayout(null);
		
		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(276, 16, 182, 86);
		contentPaneGerarExtrato.add(lblEbank);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(116, 284, 500, 300);
		contentPaneGerarExtrato.add(scrollPane);

		JTextArea textAreaExtrato = new JTextArea();
		textAreaExtrato.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(textAreaExtrato);
		textAreaExtrato.setEditable(false);

		JLabel lblCliente = new JLabel("Cliente: " + Operacoes.buscarNomeClientePorNumeroConta(numeroConta));
		lblCliente.setFont(new Font("Arial", Font.PLAIN, 30));
		lblCliente.setBounds(147, 107, 450, 40);
		contentPaneGerarExtrato.add(lblCliente);

		JLabel lblInformacoesConta = new JLabel("Conta: " + Operacoes.buscarInformacoesClientePorNumeroConta(numeroConta));
		lblInformacoesConta.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInformacoesConta.setBounds(147, 158, 450, 40);
		contentPaneGerarExtrato.add(lblInformacoesConta);

		JLabel lblSaldo = new JLabel("Saldo: " + String.format("R$%.2f", Operacoes.buscarSaldoClientePorNumeroConta(numeroConta)));
		lblSaldo.setFont(new Font("Arial", Font.PLAIN, 30));
		lblSaldo.setBounds(147, 209, 450, 40);
		contentPaneGerarExtrato.add(lblSaldo);

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
		btnCancelar.setBounds(434, 622, 182, 44);
		contentPaneGerarExtrato.add(btnCancelar);
		
		JButton btnGerarExtrato = new JButton("Gerar Extrato");
		btnGerarExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Transacao> arrayListTransacoes = Operacoes.gerarExtrato(numeroConta);

				if (arrayListTransacoes != null) {
					for (int i = 0; i < arrayListTransacoes.size(); i++) {
						textAreaExtrato.setText(textAreaExtrato.getText() + "Tipo: " + arrayListTransacoes.get(i).getTipo() + "\n");
						textAreaExtrato.setText(textAreaExtrato.getText() + "Conta de Entrada: " + arrayListTransacoes.get(i).getNumeroContaEntrada() + "\n");
						textAreaExtrato.setText(textAreaExtrato.getText() + "Conta da Saída: " + arrayListTransacoes.get(i).getNumeroContaSaida() + "\n");
						textAreaExtrato.setText(textAreaExtrato.getText() + "Data: " + arrayListTransacoes.get(i).getData() + "\n");
						textAreaExtrato.setText(textAreaExtrato.getText() + "Valor: R$" + String.format("%.2f", arrayListTransacoes.get(i).getValor()) + "\n");
						textAreaExtrato.setText(textAreaExtrato.getText() + "--------------------------------------------------\n");
					}
				} else {
					textAreaExtrato.setText("A conta ainda não possui nenhuma movimentação.");
				}
			}
		});
		btnGerarExtrato.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGerarExtrato.setBounds(237, 622, 182, 44);
		contentPaneGerarExtrato.add(btnGerarExtrato);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnGerarExtrato, btnCancelar}));

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
		JRootPane rootPane = SwingUtilities.getRootPane(btnGerarExtrato); 
		rootPane.setDefaultButton(btnGerarExtrato);
	}
}
