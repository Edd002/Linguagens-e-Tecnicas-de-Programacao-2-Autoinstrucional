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
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.action.Operacoes;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;

public class CadastrarContaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblCPF;
	private JLabel lblEndereco;
	private JTextField textFieldEndereco;
	private JLabel lblOcupacao;
	private JTextField textFieldOcupacao;
	private JFormattedTextField formattedTextFieldRenda;
	private JLabel lblRenda;
	private JPasswordField passwordFieldSenha;
	private JLabel lblSenha;
	private JButton btnCancelar;
	private JButton btnCadastrar;
	private JLabel lblTipo;
	private JPasswordField passwordFieldConfirmarSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarContaCliente frame = new CadastrarContaCliente();
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
	public CadastrarContaCliente() {
		setTitle("Cadastrar Conta-Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarContaCliente.class.getResource("/model/images/e-bank2.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEbank = new JLabel("E-Bank");
		lblEbank.setFont(new Font("Arial", Font.BOLD, 50));
		lblEbank.setBounds(265, 0, 182, 86);
		contentPane.add(lblEbank);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNome.setBounds(37, 106, 203, 24);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(255, 102, 439, 32);
		contentPane.add(textFieldNome);

		lblCPF = new JLabel("CPF:");
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

		lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEndereco.setBounds(37, 250, 203, 24);
		contentPane.add(lblEndereco);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBounds(255, 246, 439, 32);
		contentPane.add(textFieldEndereco);

		lblOcupacao = new JLabel("Ocupa\u00E7\u00E3o:");
		lblOcupacao.setFont(new Font("Arial", Font.PLAIN, 20));
		lblOcupacao.setBounds(37, 298, 203, 24);
		contentPane.add(lblOcupacao);

		textFieldOcupacao = new JTextField();
		textFieldOcupacao.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldOcupacao.setColumns(10);
		textFieldOcupacao.setBounds(255, 294, 439, 32);
		contentPane.add(textFieldOcupacao);

		lblRenda = new JLabel("Renda (R$):");
		lblRenda.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRenda.setBounds(37, 346, 203, 24);
		contentPane.add(lblRenda);

		formattedTextFieldRenda = new JFormattedTextField();
		formattedTextFieldRenda.setFont(new Font("Arial", Font.PLAIN, 20));
		formattedTextFieldRenda.setBounds(255, 342, 439, 32);
		contentPane.add(formattedTextFieldRenda);

		// Máscara de moeda
		DecimalFormat decimalFormatmatRenda = new DecimalFormat("#,###,###.00") ;
        NumberFormatter numberFormatterRenda = new NumberFormatter(decimalFormatmatRenda);
        numberFormatterRenda.setFormat(decimalFormatmatRenda);
        numberFormatterRenda.setAllowsInvalid(false);
        formattedTextFieldRenda.setFormatterFactory(new DefaultFormatterFactory(numberFormatterRenda));
        
        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        lblSenha.setBounds(37, 394, 203, 24);
        contentPane.add(lblSenha);
        
        passwordFieldSenha = new JPasswordField();
        passwordFieldSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordFieldSenha.setBounds(255, 390, 439, 32);
        contentPane.add(passwordFieldSenha);

        lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 20));
        lblTipo.setBounds(37, 490, 203, 24);
        contentPane.add(lblTipo);
        
        JComboBox comboBoxTipo = new JComboBox();
        comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Corrente", "Poupan\u00E7a", "Sal\u00E1rio", "Universit\u00E1ria"}));
        comboBoxTipo.setFont(new Font("Arial", Font.PLAIN, 20));
        comboBoxTipo.setBounds(255, 486, 439, 32);
        contentPane.add(comboBoxTipo);
        
        JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
        lblConfirmarSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        lblConfirmarSenha.setBounds(37, 442, 203, 24);
        contentPane.add(lblConfirmarSenha);
        
        passwordFieldConfirmarSenha = new JPasswordField();
        passwordFieldConfirmarSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordFieldConfirmarSenha.setBounds(255, 438, 439, 32);
        contentPane.add(passwordFieldConfirmarSenha);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
		lblDataNascimento.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDataNascimento.setBounds(37, 202, 203, 24);
		contentPane.add(lblDataNascimento);
		
		JFormattedTextField formattedTextFieldDataNascimento = new JFormattedTextField();
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

		btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String erro = null;

        		erro = Operacoes.validarContaCliente("insert", textFieldNome.getText(), formattedTextFieldCPF.getText(), formattedTextFieldDataNascimento.getText(), textFieldEndereco.getText(), textFieldOcupacao.getText(), formattedTextFieldRenda.getText().replace(",", "_").replace(".", "").replace("_", "."), String.valueOf(passwordFieldSenha.getPassword()), String.valueOf(passwordFieldConfirmarSenha.getPassword()), comboBoxTipo.getSelectedItem().toString());

        		if (erro == null) {
        			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
        			GerenciarContaCliente gerenciarContaCliente = new GerenciarContaCliente();
    				gerenciarContaCliente.setVisible(true);
    				gerenciarContaCliente.setLocationRelativeTo(null);
    				setVisible(false);
        		} else {
        			JOptionPane.showMessageDialog(null, erro, "Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnCadastrar.setBounds(379, 534, 150, 44);
        contentPane.add(btnCadastrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GerenciarContaCliente gerenciarContaCliente = new GerenciarContaCliente();
				gerenciarContaCliente.setVisible(true);
				gerenciarContaCliente.setLocationRelativeTo(null);
				setVisible(false);
        	}
        });
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnCancelar.setBounds(544, 534, 150, 44);
        contentPane.add(btnCancelar);

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
	
	    // Definir botão padrão para a tecla ENTER
		JRootPane rootPane = SwingUtilities.getRootPane(btnCadastrar); 
		rootPane.setDefaultButton(btnCadastrar);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldNome, formattedTextFieldCPF, formattedTextFieldDataNascimento, textFieldEndereco, textFieldOcupacao, formattedTextFieldRenda, passwordFieldSenha, passwordFieldConfirmarSenha, comboBoxTipo, btnCadastrar, btnCancelar}));
	}
}
