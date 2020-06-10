package view.administer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import controller.structure.Cliente;
import controller.structure.Conta;
import controller.structure.Transacao;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class GerarRelatorios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneGerarRelatorios;
	private JTable tableContaClientes;
	private JTable tableMovimentacoesDiarias;
	private JTable tableClientesQuantidadeTransacoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarRelatorios frame = new GerarRelatorios();
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
	public GerarRelatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GerarRelatorios.class.getResource("/model/images/e-bank2.png")));
		setTitle("Relat\u00F3rios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1300, 830);
		contentPaneGerarRelatorios = new JPanel();
		contentPaneGerarRelatorios.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneGerarRelatorios);
		contentPaneGerarRelatorios.setLayout(null);
		
		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(550, 16, 182, 86);
		contentPaneGerarRelatorios.add(lblEbank);
		
		JScrollPane scrollPaneContaCliente = new JScrollPane();
		scrollPaneContaCliente.setBounds(15, 152, 1264, 150);
		contentPaneGerarRelatorios.add(scrollPaneContaCliente);
		
		tableContaClientes = new JTable();
		tableContaClientes.setRowSelectionAllowed(false);
		scrollPaneContaCliente.setViewportView(tableContaClientes);
		tableContaClientes.setToolTipText("");
		tableContaClientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ativo", "Nome", "CPF", "Data de Nascimento", "Endere\u00E7o", "Ocupa\u00E7\u00E3o", "Renda", "N\u00FAmero da Conta", "Tipo da Conta", "Saldo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableContaClientes.getColumnModel().getColumn(0).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableContaClientes.getColumnModel().getColumn(0).setMinWidth(50);
		tableContaClientes.getColumnModel().getColumn(1).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableContaClientes.getColumnModel().getColumn(1).setMinWidth(150);
		tableContaClientes.getColumnModel().getColumn(2).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableContaClientes.getColumnModel().getColumn(2).setMinWidth(100);
		tableContaClientes.getColumnModel().getColumn(3).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(3).setPreferredWidth(170);
		tableContaClientes.getColumnModel().getColumn(3).setMinWidth(170);
		tableContaClientes.getColumnModel().getColumn(4).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableContaClientes.getColumnModel().getColumn(4).setMinWidth(150);
		tableContaClientes.getColumnModel().getColumn(5).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(5).setPreferredWidth(125);
		tableContaClientes.getColumnModel().getColumn(5).setMinWidth(125);
		tableContaClientes.getColumnModel().getColumn(6).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableContaClientes.getColumnModel().getColumn(6).setMinWidth(100);
		tableContaClientes.getColumnModel().getColumn(7).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(7).setPreferredWidth(150);
		tableContaClientes.getColumnModel().getColumn(7).setMinWidth(150);
		tableContaClientes.getColumnModel().getColumn(8).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(8).setPreferredWidth(150);
		tableContaClientes.getColumnModel().getColumn(8).setMinWidth(150);
		tableContaClientes.getColumnModel().getColumn(9).setResizable(false);
		tableContaClientes.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableContaClientes.getColumnModel().getColumn(9).setMinWidth(100);
		tableContaClientes.setFont(new Font("Arial", Font.PLAIN, 12));

		JScrollPane scrollPaneMovimentacoesDiarias = new JScrollPane();
		scrollPaneMovimentacoesDiarias.setBounds(15, 318, 1264, 150);
		contentPaneGerarRelatorios.add(scrollPaneMovimentacoesDiarias);
		
		tableMovimentacoesDiarias = new JTable();
		scrollPaneMovimentacoesDiarias.setViewportView(tableMovimentacoesDiarias);
		tableMovimentacoesDiarias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tipo", "N\u00FAmero da Conta de Entrada", "N\u00FAmero da Conta de Sa\u00EDda", "Cliente da Conta de Entrada", "Cliente da Conta de Sa\u00EDda", "Data", "Valor da Transa\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMovimentacoesDiarias.getColumnModel().getColumn(0).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableMovimentacoesDiarias.getColumnModel().getColumn(0).setMinWidth(100);
		tableMovimentacoesDiarias.getColumnModel().getColumn(1).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(1).setPreferredWidth(220);
		tableMovimentacoesDiarias.getColumnModel().getColumn(1).setMinWidth(220);
		tableMovimentacoesDiarias.getColumnModel().getColumn(2).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(2).setPreferredWidth(210);
		tableMovimentacoesDiarias.getColumnModel().getColumn(2).setMinWidth(210);
		tableMovimentacoesDiarias.getColumnModel().getColumn(3).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(3).setPreferredWidth(210);
		tableMovimentacoesDiarias.getColumnModel().getColumn(3).setMinWidth(210);
		tableMovimentacoesDiarias.getColumnModel().getColumn(4).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableMovimentacoesDiarias.getColumnModel().getColumn(4).setMinWidth(200);
		tableMovimentacoesDiarias.getColumnModel().getColumn(5).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(5).setPreferredWidth(175);
		tableMovimentacoesDiarias.getColumnModel().getColumn(5).setMinWidth(175);
		tableMovimentacoesDiarias.getColumnModel().getColumn(6).setResizable(false);
		tableMovimentacoesDiarias.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableMovimentacoesDiarias.getColumnModel().getColumn(6).setMinWidth(150);
		tableMovimentacoesDiarias.setFont(new Font("Arial", Font.PLAIN, 12));
		tableMovimentacoesDiarias.setRowSelectionAllowed(false);
		
		JScrollPane scrollPaneClientesMaisTransacoes = new JScrollPane();
		scrollPaneClientesMaisTransacoes.setBounds(15, 484, 1264, 150);
		contentPaneGerarRelatorios.add(scrollPaneClientesMaisTransacoes);
		
		tableClientesQuantidadeTransacoes = new JTable();
		scrollPaneClientesMaisTransacoes.setViewportView(tableClientesQuantidadeTransacoes);
		tableClientesQuantidadeTransacoes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Quantidade de Transa\u00E7\u00F5es", "CPF do Cliente", "Nome do Cliente", "N\u00FAmero da Conta do Cliente"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(0).setResizable(false);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(0).setMinWidth(200);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(1).setResizable(false);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(1).setMinWidth(200);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(2).setResizable(false);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(2).setMinWidth(200);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(3).setResizable(false);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(3).setPreferredWidth(230);
		tableClientesQuantidadeTransacoes.getColumnModel().getColumn(3).setMinWidth(230);
		tableClientesQuantidadeTransacoes.setRowSelectionAllowed(false);
		tableClientesQuantidadeTransacoes.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton btnVisualizarContaClientes = new JButton("Visualizar Conta-Clientes");
		btnVisualizarContaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Conta> arrayListContas = Operacoes.buscarContas();
				ArrayList<Cliente> arrayListClientes = Operacoes.buscarClientes();
				DefaultTableModel tabelaContaClientes = (DefaultTableModel) tableContaClientes.getModel();
				Object rowData[] = new Object[10];

				if (tabelaContaClientes.getRowCount() == 0) {
					for (int i = 0; i < arrayListClientes.size(); i++) {
						if (arrayListClientes.get(i).isAtivo())
							rowData[0] = "Sim";
						else
							rowData[0] = "Não";
						rowData[1] = arrayListClientes.get(i).getNome();
						rowData[2] = arrayListClientes.get(i).getCpf();
						rowData[3] = arrayListClientes.get(i).getDataNascimento();
						rowData[4] = arrayListClientes.get(i).getEndereco();
						rowData[5] = arrayListClientes.get(i).getOcupacao();
						rowData[6] = String.format("%.2f", arrayListClientes.get(i).getRenda());
						rowData[7] = arrayListContas.get(i).getNumero();
						rowData[8] = arrayListContas.get(i).getTipo();
						rowData[9] = String.format("%.2f", arrayListContas.get(i).getSaldo());
	
						tabelaContaClientes.addRow(rowData);
					}
				} else {
					JOptionPane.showMessageDialog(null, "O relatório de conta-clientes já está em exibição.", "Aviso de Busca", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnVisualizarContaClientes.setFont(new Font("Arial", Font.PLAIN, 20));
		btnVisualizarContaClientes.setBounds(142, 672, 500, 44);
		contentPaneGerarRelatorios.add(btnVisualizarContaClientes);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciarContaCliente gerenciarContaCliente = new GerenciarContaCliente();
				gerenciarContaCliente.setVisible(true);
				gerenciarContaCliente.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(652, 727, 500, 44);
		contentPaneGerarRelatorios.add(btnCancelar);
		
		JButton btnRelatorioClientesMaisTransacoes = new JButton("Gerar Relat\u00F3rio de Clientes com Mais Transa\u00E7\u00F5es");
		btnRelatorioClientesMaisTransacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<Cliente, Integer> hashMapQuantidadeTransacoesClientes = Operacoes.gerarRelatorioClientesQuantidadeTransacoes();
				DefaultTableModel movimentacoesDiarias = (DefaultTableModel) tableClientesQuantidadeTransacoes.getModel();
				Object rowData[] = new Object[4];

				if (movimentacoesDiarias.getRowCount() == 0) {
					Set<Cliente> chaves = hashMapQuantidadeTransacoesClientes.keySet();
					for (Cliente chave : chaves) {
						rowData[0] = hashMapQuantidadeTransacoesClientes.get(chave);
						rowData[1] = chave.getCpf();
						rowData[2] = chave.getNome();
						rowData[3] = Operacoes.buscarContaPorCPF(chave.getCpf()).getNumero();

						movimentacoesDiarias.addRow(rowData);
					}
				} else {
					JOptionPane.showMessageDialog(null, "O relatório de quantidade de transações já está em exibição.", "Aviso de Busca", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnRelatorioClientesMaisTransacoes.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRelatorioClientesMaisTransacoes.setBounds(142, 727, 500, 44);
		contentPaneGerarRelatorios.add(btnRelatorioClientesMaisTransacoes);
		
		JButton btnGerarRelatorioMovimentacoesDiarias = new JButton("Gerar Relat\u00F3rio de Movimenta\u00E7\u00F5es Di\u00E1rias");
		btnGerarRelatorioMovimentacoesDiarias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Transacao> arrayListTransacao = Operacoes.buscarTransacoes();
				DefaultTableModel movimentacoesDiarias = (DefaultTableModel) tableMovimentacoesDiarias.getModel();
				Object rowData[] = new Object[7];

				if (movimentacoesDiarias.getRowCount() == 0) {
					for (int i = 0; i < arrayListTransacao.size(); i++) {
						rowData[0] = arrayListTransacao.get(i).getTipo();
						rowData[1] = arrayListTransacao.get(i).getNumeroContaEntrada();
						rowData[2] = arrayListTransacao.get(i).getNumeroContaSaida();
						rowData[3] = Operacoes.buscarNomeClientePorNumeroConta(arrayListTransacao.get(i).getNumeroContaEntrada());
							if (rowData[3] == null)
								rowData[3] = "N/A";
						rowData[4] = Operacoes.buscarNomeClientePorNumeroConta(arrayListTransacao.get(i).getNumeroContaSaida());
							if (rowData[4] == null)
								rowData[4] = "N/A";
						rowData[5] = arrayListTransacao.get(i).getData();
						rowData[6] = String.format("%.2f", arrayListTransacao.get(i).getValor());
	
						movimentacoesDiarias.addRow(rowData);
					}
				} else {
					JOptionPane.showMessageDialog(null, "O relatório de movimentações diárias já está em exibição.", "Aviso de Busca", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnGerarRelatorioMovimentacoesDiarias.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGerarRelatorioMovimentacoesDiarias.setBounds(652, 672, 500, 44);
		contentPaneGerarRelatorios.add(btnGerarRelatorioMovimentacoesDiarias);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnVisualizarContaClientes, btnGerarRelatorioMovimentacoesDiarias, btnRelatorioClientesMaisTransacoes, btnCancelar}));

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
				GerenciarContaCliente gerenciarContaCliente = new GerenciarContaCliente();
				gerenciarContaCliente.setVisible(true);
				gerenciarContaCliente.setLocationRelativeTo(null);
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
