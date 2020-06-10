package controller.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import controller.structure.Cliente;
import controller.structure.Conta;
import controller.structure.Transacao;
import model.DAO;

public class Operacoes {
	// ------------------------------- VALIDAÇÕES E AUTENTIFICAÇÕES ---------------------------------
	public static String validarContaCliente(String opcaoCRUD, String nomeCliente, String cpfCliente, String dataNascimentoCliente, String enderecoCliente, String ocupacaoCliente, String rendaCliente, String senhaConta, String confirmarSenhaConta, String tipoConta) {
		Cliente cliente = new Cliente();
		Conta conta = new Conta();
		String erro = "";

		if (nomeCliente != null) {
			if (nomeCliente.equals(""))
				erro += "O nome não pode estar em branco.\n";
			else
				cliente.setNome(nomeCliente);
		}

		if (cpfCliente != null) {
			try {
				Long.parseLong(cpfCliente.substring(0, 3) + cpfCliente.substring(4, 7) + cpfCliente.substring(8, 11) + cpfCliente.substring(12, 14));
				cliente.setCpf(cpfCliente);
				conta.setCpfCliente(cpfCliente);
			} catch (Exception exception) {
				erro += "O CPF deve conter 14 caracteres.\n";
			}
		}

		if (dataNascimentoCliente != null) {
			try {
				Locale localeBR = new Locale("pt", "BR");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", localeBR);
				cliente.setDataNascimento(simpleDateFormat.format(dateFormat.parse(dataNascimentoCliente)));
			} catch (Exception exception) {
				erro += "Data em formato inválido.\n";
			}
		}

		if (enderecoCliente != null) {
			if (enderecoCliente.equals(""))
				erro += "O endereço não pode estar em branco.\n";
			else
				cliente.setEndereco(enderecoCliente);
		}

		if (ocupacaoCliente != null) {
			if (ocupacaoCliente.equals(""))
				erro += "A ocupação não pode estar em branco.\n";
			else
				cliente.setOcupacao(ocupacaoCliente);
		}

		if (rendaCliente != null) {
			try {
				cliente.setRenda(Double.parseDouble(rendaCliente));
			} catch (Exception exception) {
				erro += "Renda em formato inválido.\n";
			}
		}

		if (senhaConta != null) {
			if (senhaConta.equals(""))
				erro += "A senha não pode estar em branco.\n";
			else
				conta.setSenha(senhaConta);
		}

		if (confirmarSenhaConta != null) {
			if (!confirmarSenhaConta.equals(senhaConta))
				erro += "Campos senha e confirmar senha devem ser iguais.\n";
		}

		if (tipoConta != null) {
			conta.setTipo(tipoConta);
		}

		if (erro.equals("")) {
			if (opcaoCRUD.equals("insert")) {
				Random random = new Random();
				int numeroConta;
				if (conta.getTipo().equals("Corrente"))
					numeroConta = random.nextInt(900000000) + 100000000;
				else if (conta.getTipo().equals("Poupança"))
					numeroConta = random.nextInt(90000000) + 10000000;
				else if (conta.getTipo().equals("Salário"))
					numeroConta = random.nextInt(9000000) + 1000000;
				else
					numeroConta = random.nextInt(900000) + 100000;

				cliente.setAtivo(true);
				conta.setAtivo(true);
				conta.setNumero(Integer.toString(numeroConta));
				conta.setSaldo(0);

				erro = inserirContaCliente(conta, cliente);
			} else if (opcaoCRUD.equals("update")) {
				cliente.setAtivo(true);
				conta.setAtivo(true);

				erro = atualizarContaCliente(conta, cliente);
			} else if (opcaoCRUD.equals("delete")) {
				cliente.setAtivo(false);
				conta.setAtivo(false);

				erro = desativarContaCliente(conta, cliente);
			} else {
				erro = "Erro ao validar operação.";
			}

			return erro;
		} else {
			return erro;
		}
	}

	public static String autentificarContaClienteLogin(String numeroConta, String senhaConta) {
		Conta conta = DAO.selectContaByNumberAndPassword(numeroConta, senhaConta);
		
		if (conta != null)
			return DAO.selectClienteByCPF(conta.getCpfCliente()).getNome();
		else
			return null;
	}
	// ----------------------------------------------------------------------------------------------


	// ------------------------------------------- CRUD ---------------------------------------------
	private static String inserirContaCliente(Conta conta, Cliente cliente) {
		if (!DAO.insertContaCliente(conta, cliente))
			return "Erro ao inserir Conta-Cliente";

		return null;
	}

	private static String atualizarContaCliente(Conta conta, Cliente cliente) {
		if (!DAO.updateContaCliente(conta, cliente))
			return "Erro ao alterar Conta-Cliente";

		return null;
	}

	private static String desativarContaCliente(Conta conta, Cliente cliente) {
		if (!DAO.deleteContaCliente(conta, cliente))
			return "Erro ao desativar Conta-Cliente";

		return null;
	}

	public static ArrayList<Conta> buscarContas() {
		return DAO.selectConta();
	}

	public static ArrayList<Cliente> buscarClientes() {
		return DAO.selectCliente();
	}

	public static ArrayList<Transacao> buscarTransacoes() {
		return DAO.selectTransacao();
	}

	public static Conta buscarContaPorCPF(String CPF) {
		return DAO.selectContaByCPF(CPF);
	}

	public static Cliente buscarClientePorCPF(String CPF) {
		return DAO.selectClienteByCPF(CPF);
	}

	public static String buscarNomeClientePorNumeroConta(String numeroConta) {
		return DAO.selectNomeClienteByNumeroConta(numeroConta);
	}

	public static String buscarInformacoesClientePorNumeroConta(String numeroConta) {
		return DAO.selectInformacoesContaByNumeroConta(numeroConta);
	}

	public static double buscarSaldoClientePorNumeroConta(String numeroConta) {
		return DAO.selectSaldoContaByNumeroConta(numeroConta);
	}
	// ----------------------------------------------------------------------------------------------


	// ----------------------------------------- TRANSAÇÕES -----------------------------------------
	public static boolean realizarDeposito(String numeroConta, String valorDeposito) {
		try {
			Double.parseDouble(valorDeposito);
			if (DAO.deposit(numeroConta, Double.parseDouble(valorDeposito))) {
				gerarTransacao("Depósito", numeroConta, "N/A", null, valorDeposito);
				return true;
			}
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	public static boolean realizarSaque(String numeroConta, String valorSaque) {
		try {
			Double.parseDouble(valorSaque);
			if (DAO.withdraw(numeroConta, Double.parseDouble(valorSaque))) {
				gerarTransacao("Saque", "N/A", numeroConta, null, valorSaque);
				return true;
			}
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	public static boolean realizarTransferencia(String numeroContaEntrada, String numeroContaSaida, String valorTransferencia) {
		try {
			Double.parseDouble(valorTransferencia);
			if (DAO.withdraw(numeroContaSaida, Double.parseDouble(valorTransferencia))) {
				if (DAO.deposit(numeroContaEntrada, Double.parseDouble(valorTransferencia))) {
					gerarTransacao("Transferência", numeroContaEntrada, numeroContaSaida, null, valorTransferencia);
					return true;
				}
			}
		} catch (Exception exception) {
			return false;
		}

		return false;
	}

	private static void gerarTransacao(String tipo, String numeroContaEntrada, String numeroContaSaida, String data, String valor) {
		Transacao transacao = new Transacao();

		transacao.setTipo(tipo);
		transacao.setNumeroContaEntrada(numeroContaEntrada);
		transacao.setNumeroContaSaida(numeroContaSaida);
		transacao.setData((new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", (new Locale("pt", "BR")))).format(new Date()));
		transacao.setValor(Double.parseDouble(valor));

		DAO.insertTransacao(transacao);
	}

	public static ArrayList<Transacao> gerarExtrato(String numeroConta) {
		return DAO.selectTransacaoByNumeroConta(numeroConta);
	}

	public static HashMap<Cliente, Integer> gerarRelatorioClientesQuantidadeTransacoes() {
		HashMap<Conta, Integer> hashMapQuantidadeTransacoesContas = new HashMap<>();
		ArrayList<Conta> arrayListContas = DAO.selectConta();
		ArrayList<Transacao> arrayListTransacoes = DAO.selectTransacao();

		for (int i = 0; i < arrayListTransacoes.size(); i++) {
			for (int j = 0; j < arrayListContas.size(); j++) {
				if (arrayListContas.get(j).getNumero().equals(arrayListTransacoes.get(i).getNumeroContaEntrada()) || arrayListContas.get(j).getNumero().equals(arrayListTransacoes.get(i).getNumeroContaSaida())) {
					if (hashMapQuantidadeTransacoesContas.get(arrayListContas.get(j)) != null)
						hashMapQuantidadeTransacoesContas.put(arrayListContas.get(j), hashMapQuantidadeTransacoesContas.get(arrayListContas.get(j)) + 1);
					else
						hashMapQuantidadeTransacoesContas.put(arrayListContas.get(j), 0);
				}
			}
		}

		HashMap<Cliente, Integer> hashMapQuantidadeTransacoesClientes = new HashMap<>();
		Set<Conta> chaves = hashMapQuantidadeTransacoesContas.keySet();
		for (Conta chave : chaves) {
			if (chave.isAtivo())
				hashMapQuantidadeTransacoesClientes.put(DAO.selectClienteByCPF(chave.getCpfCliente()), hashMapQuantidadeTransacoesContas.get(chave));
		}
		
		return hashMapQuantidadeTransacoesClientes;
	}
	// ----------------------------------------------------------------------------------------------
}