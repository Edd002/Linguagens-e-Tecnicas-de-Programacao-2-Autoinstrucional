package view.administer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import controller.structure.Cliente;
import controller.structure.Conta;

import java.awt.Component;
import java.awt.event.ActionListener;

public class DesativarContaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEndereco;
	private JTextField textFieldOcupacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesativarContaCliente frame = new DesativarContaCliente();
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
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public DesativarContaCliente() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DesativarContaCliente.class.getResource("/model/images/e-bank2.png")));
		setTitle("Desativar Conta-Cliente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(265, 0, 182, 86);
		contentPane.add(lblEbank);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldNome.setEditable(false);
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(255, 102, 439, 32);
		contentPane.add(textFieldNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNome.setBounds(37, 107, 203, 23);
		contentPane.add(lblNome);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCPF.setBounds(37, 154, 203, 24);
		contentPane.add(lblCPF);

		JFormattedTextField formattedTextFieldCPF = new JFormattedTextField();
		formattedTextFieldCPF.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldCPF.setBounds(255, 150, 439, 32);
		contentPane.add(formattedTextFieldCPF);

		// Máscara de CPF
		MaskFormatter maskFormatterCPF = null;
		try {
			maskFormatterCPF = new MaskFormatter("###.###.###-##");
			maskFormatterCPF.install(formattedTextFieldCPF);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEndereco.setBounds(37, 250, 203, 24);
		contentPane.add(lblEndereco);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldEndereco.setEditable(false);
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBounds(255, 246, 439, 32);
		contentPane.add(textFieldEndereco);

		JLabel lblOcupacao = new JLabel("Ocupa\u00E7\u00E3o:");
		lblOcupacao.setFont(new Font("Arial", Font.PLAIN, 20));
		lblOcupacao.setBounds(37, 298, 203, 24);
		contentPane.add(lblOcupacao);

		textFieldOcupacao = new JTextField();
		textFieldOcupacao.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldOcupacao.setEditable(false);
		textFieldOcupacao.setColumns(10);
		textFieldOcupacao.setBounds(255, 294, 439, 32);
		contentPane.add(textFieldOcupacao);

		JLabel lblRenda = new JLabel("Renda (R$):");
		lblRenda.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRenda.setBounds(37, 346, 203, 24);
		contentPane.add(lblRenda);

		JFormattedTextField formattedTextFieldRenda = new JFormattedTextField();
		formattedTextFieldRenda.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldRenda.setEditable(false);
		formattedTextFieldRenda.setBounds(255, 342, 439, 32);
		contentPane.add(formattedTextFieldRenda);

		// Máscara de moeda
		DecimalFormat decimalFormatmatRenda = new DecimalFormat("#,###,###.00");
		NumberFormatter numberFormatterRenda = new NumberFormatter(decimalFormatmatRenda);
		numberFormatterRenda.setFormat(decimalFormatmatRenda);
		numberFormatterRenda.setAllowsInvalid(false);
		formattedTextFieldRenda.setFormatterFactory(new DefaultFormatterFactory(numberFormatterRenda));

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTipo.setBounds(37, 394, 203, 24);
		contentPane.add(lblTipo);

		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setEnabled(false);
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "Corrente", "Poupan\u00E7a", "Sal\u00E1rio", "Universit\u00E1ria" }));
		comboBoxTipo.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBoxTipo.setBounds(255, 390, 439, 32);
		contentPane.add(comboBoxTipo);

		JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
		lblDataNascimento.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDataNascimento.setBounds(37, 202, 203, 24);
		contentPane.add(lblDataNascimento);
		
		JFormattedTextField formattedTextFieldDataNascimento = new JFormattedTextField();
		formattedTextFieldDataNascimento.setEditable(false);
		formattedTextFieldDataNascimento.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldDataNascimento.setBounds(255, 198, 439, 32);
		contentPane.add(formattedTextFieldDataNascimento);

		// Máscara de Data de Nascimento
		MaskFormatter maskFormatterDataNascimento = null;
		try {
			maskFormatterDataNascimento = new MaskFormatter("##/##/####");
			maskFormatterDataNascimento.install(formattedTextFieldDataNascimento);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		Conta conta = new Conta();
        		Cliente cliente = new Cliente();

        		conta = Operacoes.buscarContaPorCPF(formattedTextFieldCPF.getText());
        		cliente = Operacoes.buscarClientePorCPF(formattedTextFieldCPF.getText());

        		if (conta != null && cliente != null) {
        			textFieldNome.setText(cliente.getNome());

        			formattedTextFieldCPF.setEditable(false);
        			formattedTextFieldCPF.setText(cliente.getCpf());

        			try {
	        			Locale localeBR = new Locale("pt", "BR");
	    				DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
	    				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", localeBR);
	    				formattedTextFieldDataNascimento.setText(simpleDateFormat.format(dateFormat.parse(cliente.getDataNascimento())));
    				} catch (Exception exception) {
    					JOptionPane.showMessageDialog(null, "Não foi possível recuperar a data do sistema de arquivos.", "Erro ao Buscar", JOptionPane.ERROR_MESSAGE);
    				}

        			textFieldEndereco.setText(cliente.getEndereco());

        			textFieldOcupacao.setText(cliente.getOcupacao());

        			formattedTextFieldRenda.setText(String.format("%.2f", cliente.getRenda()));

        			comboBoxTipo.setSelectedItem(conta.getTipo());
        		} else {
        			JOptionPane.showMessageDialog(null, "O CPF não foi encontrado.", "Erro ao Buscar", JOptionPane.ERROR_MESSAGE);
        		}
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBuscar.setBounds(214, 438, 150, 44);
		contentPane.add(btnBuscar);

		JButton btnDesativar = new JButton("Desativar");
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String erro = null;

        		if (!formattedTextFieldCPF.isEditable()) {
        			erro = Operacoes.validarContaCliente("delete", null, formattedTextFieldCPF.getText(), null, null, null, null, null, null, null);

	        		if (erro == null) {
	        			JOptionPane.showMessageDialog(null, "Cliente desativado com sucesso.", "Desativação Realizada", JOptionPane.INFORMATION_MESSAGE);
	        			GerenciarContaCliente gerenciarContaCliente = new GerenciarContaCliente();
	    				gerenciarContaCliente.setVisible(true);
	    				gerenciarContaCliente.setLocationRelativeTo(null);
	    				setVisible(false);
	        		} else {
	        			JOptionPane.showMessageDialog(null, erro, "Erro ao Desativar", JOptionPane.ERROR_MESSAGE);
	        		}
        		} else {
        			JOptionPane.showMessageDialog(null, "É necessário buscar o cliente pelo CPF antes de desativá-lo.", "Erro ao Desativar", JOptionPane.ERROR_MESSAGE);
        		}
			}
		});
		btnDesativar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDesativar.setBounds(379, 438, 150, 44);
		contentPane.add(btnDesativar);

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
		btnCancelar.setBounds(544, 438, 150, 44);
		contentPane.add(btnCancelar);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{formattedTextFieldCPF, btnBuscar, btnDesativar, btnCancelar}));

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
