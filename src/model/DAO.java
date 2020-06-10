package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import controller.structure.Cliente;
import controller.structure.Conta;
import controller.structure.Transacao;

public class DAO {
	// -------------------------------------------- CRUD --------------------------------------------
	private static RandomAccessFile randomAccessFile(String file) {
		RandomAccessFile randomAccessFile;
		try {
			randomAccessFile = new RandomAccessFile("src\\model\\files\\" + file, "rw");
		} catch (FileNotFoundException exception) {
			return null;
		}
		return randomAccessFile;
	}

	public static boolean insertContaCliente(Conta conta, Cliente cliente) {
		RandomAccessFile arquivoClientes = randomAccessFile("clientes.txt");
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoClientes.seek(arquivoClientes.length());
			arquivoContas.seek(arquivoContas.length());

			arquivoClientes.writeBoolean(cliente.isAtivo());
			arquivoClientes.writeUTF(cliente.getNome());
			arquivoClientes.writeUTF(cliente.getCpf());
			arquivoClientes.writeUTF(cliente.getDataNascimento());
			arquivoClientes.writeUTF(cliente.getEndereco());
			arquivoClientes.writeUTF(cliente.getOcupacao());
			arquivoClientes.writeDouble(cliente.getRenda());
			arquivoContas.writeBoolean(conta.isAtivo());
			arquivoContas.writeUTF(conta.getCpfCliente());
			arquivoContas.writeUTF(conta.getNumero());
			arquivoContas.writeUTF(conta.getSenha());
			arquivoContas.writeUTF(conta.getTipo());
			arquivoContas.writeDouble(conta.getSaldo());

			arquivoClientes.close();
			arquivoContas.close();
		} catch (IOException ioException) {
			return false;
		}

		return true;
	}

	public static boolean insertTransacao(Transacao transacao) {
		RandomAccessFile arquivoTransacoes = randomAccessFile("transacoes.txt");

		try {
			arquivoTransacoes.seek(arquivoTransacoes.length());

			arquivoTransacoes.writeUTF(transacao.getTipo());
			arquivoTransacoes.writeUTF(transacao.getNumeroContaEntrada());
			arquivoTransacoes.writeUTF(transacao.getNumeroContaSaida());
			arquivoTransacoes.writeUTF(transacao.getData());
			arquivoTransacoes.writeDouble(transacao.getValor());

			arquivoTransacoes.close();
		} catch (IOException ioException) {
			return false;
		}

		return true;
	}

	public static boolean updateContaCliente(Conta conta, Cliente cliente) {
		RandomAccessFile arquivoClientes = randomAccessFile("clientes.txt");
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			Conta contaInformacaoAntiga = selectContaByCPF(conta.getCpfCliente());

			arquivoClientes.seek(searchPositionClienteByCPF(cliente.getCpf()));
			arquivoContas.seek(searchPositionContaByCPF(conta.getCpfCliente()));
			arquivoClientes.writeBoolean(false);
			arquivoContas.writeBoolean(false);

			arquivoClientes.seek(arquivoClientes.length());
			arquivoContas.seek(arquivoContas.length());

			arquivoClientes.writeBoolean(cliente.isAtivo());
			arquivoClientes.writeUTF(cliente.getNome());
			arquivoClientes.writeUTF(cliente.getCpf());
			arquivoClientes.writeUTF(cliente.getDataNascimento());
			arquivoClientes.writeUTF(cliente.getEndereco());
			arquivoClientes.writeUTF(cliente.getOcupacao());
			arquivoClientes.writeDouble(cliente.getRenda());
			arquivoContas.writeBoolean(conta.isAtivo());
			arquivoContas.writeUTF(conta.getCpfCliente());
			arquivoContas.writeUTF(contaInformacaoAntiga.getNumero());
			arquivoContas.writeUTF(conta.getSenha());
			arquivoContas.writeUTF(conta.getTipo());
			arquivoContas.writeDouble(contaInformacaoAntiga.getSaldo());

			arquivoClientes.close();
			arquivoContas.close();
		} catch (IOException ioException) {
			return false;
		}

		return true;
	}

	public static boolean deleteContaCliente(Conta conta, Cliente cliente) {
		RandomAccessFile arquivoClientes = randomAccessFile("clientes.txt");
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoClientes.seek(searchPositionClienteByCPF(cliente.getCpf()));
			arquivoContas.seek(searchPositionContaByCPF(conta.getCpfCliente()));

			arquivoClientes.writeBoolean(cliente.isAtivo());
			arquivoContas.writeBoolean(conta.isAtivo());

			arquivoClientes.close();
			arquivoContas.close();
		} catch (IOException ioException) {
			return false;
		}

		return true;
	}

	public static ArrayList<Conta> selectConta() {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");
		ArrayList<Conta> arrayListContas = new ArrayList<>();

		try {
			arquivoContas.seek(0);
			while (arquivoContas.getFilePointer() != arquivoContas.length()) {
				Conta contaBuscada = new Conta();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				arrayListContas.add(contaBuscada);
			}
		} catch (IOException ioException) {
			return null;
		}

		return arrayListContas;
	}

	public static ArrayList<Cliente> selectCliente() {
		RandomAccessFile arquivoClientes = randomAccessFile("clientes.txt");
		ArrayList<Cliente> arrayListClientes = new ArrayList<>();

		try {
			arquivoClientes.seek(0);
			while (arquivoClientes.getFilePointer() != arquivoClientes.length()) {
				Cliente clienteBuscado = new Cliente();

				clienteBuscado.setAtivo(arquivoClientes.readBoolean());
				clienteBuscado.setNome(arquivoClientes.readUTF());
				clienteBuscado.setCpf(arquivoClientes.readUTF());
				clienteBuscado.setDataNascimento(arquivoClientes.readUTF());
				clienteBuscado.setEndereco(arquivoClientes.readUTF());
				clienteBuscado.setOcupacao(arquivoClientes.readUTF());
				clienteBuscado.setRenda(arquivoClientes.readDouble());

				arrayListClientes.add(clienteBuscado);
			}
		} catch (IOException ioException) {
			return null;
		}

		return arrayListClientes;
	}

	public static ArrayList<Transacao> selectTransacao() {
		RandomAccessFile arquivoTransacoes = randomAccessFile("transacoes.txt");
		ArrayList<Transacao> arrayListTransacoes = new ArrayList<>();

		try {
			arquivoTransacoes.seek(0);
			while (arquivoTransacoes.getFilePointer() != arquivoTransacoes.length()) {
				Transacao transacaoBuscada = new Transacao();

				transacaoBuscada.setTipo(arquivoTransacoes.readUTF());
				transacaoBuscada.setNumeroContaEntrada(arquivoTransacoes.readUTF());
				transacaoBuscada.setNumeroContaSaida(arquivoTransacoes.readUTF());
				transacaoBuscada.setData(arquivoTransacoes.readUTF());
				transacaoBuscada.setValor(arquivoTransacoes.readDouble());

				arrayListTransacoes.add(transacaoBuscada);
			}
		} catch (IOException ioException) {
			return null;
		}

		return arrayListTransacoes;
	}
	// ----------------------------------------------------------------------------------------------


	// ------------------------------------ SELEÇÕES ESPECÍFICAS ------------------------------------
	public static Conta selectContaByNumberAndPassword(String numeroConta, String senhaConta) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (arquivoContas.getFilePointer() != arquivoContas.length()) {
				Conta contaBuscada = new Conta();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getNumero().equals(numeroConta) && contaBuscada.getSenha().equals(senhaConta)) {
					arquivoContas.close();
					return contaBuscada;
				}
			}
		} catch (IOException ioException) {
			return null;
		}

		return null;
	}

	public static Conta selectContaByCPF(String CPF) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (arquivoContas.getFilePointer() != arquivoContas.length()) {
				Conta contaBuscada = new Conta();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getCpfCliente().equals(CPF)) {
					arquivoContas.close();
					return contaBuscada;
				}
			}
		} catch (IOException ioException) {
			return null;
		}

		return null;
	}

	public static Cliente selectClienteByCPF(String CPF) {
		RandomAccessFile arquivoClientes = randomAccessFile("clientes.txt");

		try {
			arquivoClientes.seek(0);
			while (arquivoClientes.getFilePointer() != arquivoClientes.length()) {
				Cliente clienteBuscado = new Cliente();

				clienteBuscado.setAtivo(arquivoClientes.readBoolean());
				clienteBuscado.setNome(arquivoClientes.readUTF());
				clienteBuscado.setCpf(arquivoClientes.readUTF());
				clienteBuscado.setDataNascimento(arquivoClientes.readUTF());
				clienteBuscado.setEndereco(arquivoClientes.readUTF());
				clienteBuscado.setOcupacao(arquivoClientes.readUTF());
				clienteBuscado.setRenda(arquivoClientes.readDouble());

				if (clienteBuscado.isAtivo() && clienteBuscado.getCpf().equals(CPF)) {
					arquivoClientes.close();
					return clienteBuscado;
				}
			}
		} catch (IOException ioException) {
			return null;
		}

		return null;
	}

	public static String selectNomeClienteByNumeroConta(String numeroConta) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (arquivoContas.getFilePointer() != arquivoContas.length()) {
				Conta contaBuscada = new Conta();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getNumero().equals(numeroConta)) {
					arquivoContas.close();
					return selectClienteByCPF(contaBuscada.getCpfCliente()).getNome();
				}
			}
		} catch (IOException ioException) {
			return null;
		}

		return null;
	}

	public static String selectInformacoesContaByNumeroConta(String numeroConta) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (arquivoContas.getFilePointer() != arquivoContas.length()) {
				Conta contaBuscada = new Conta();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getNumero().equals(numeroConta)) {
					arquivoContas.close();
					return contaBuscada.getTipo() + " - " + contaBuscada.getNumero();
				}
			}
		} catch (IOException ioException) {
			return null;
		}

		return null;
	}

	public static double selectSaldoContaByNumeroConta(String numeroConta) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (arquivoContas.getFilePointer() != arquivoContas.length()) {
				Conta contaBuscada = new Conta();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getNumero().equals(numeroConta)) {
					arquivoContas.close();
					return contaBuscada.getSaldo();
				}
			}
		} catch (IOException ioException) {
			return 0.0;
		}

		return 0.0;
	}

	public static ArrayList<Transacao> selectTransacaoByNumeroConta(String numeroConta) {
		RandomAccessFile arquivoTransacoes = randomAccessFile("transacoes.txt");
		ArrayList<Transacao> arrayListTransacoes = new ArrayList<>();

		try {
			arquivoTransacoes.seek(0);
			while (arquivoTransacoes.getFilePointer() != arquivoTransacoes.length()) {
				Transacao transacaoBuscada = new Transacao();

				transacaoBuscada.setTipo(arquivoTransacoes.readUTF());
				transacaoBuscada.setNumeroContaEntrada(arquivoTransacoes.readUTF());
				transacaoBuscada.setNumeroContaSaida(arquivoTransacoes.readUTF());
				transacaoBuscada.setData(arquivoTransacoes.readUTF());
				transacaoBuscada.setValor(arquivoTransacoes.readDouble());

				if (transacaoBuscada.getNumeroContaEntrada().equals(numeroConta) || transacaoBuscada.getNumeroContaSaida().equals(numeroConta))
					arrayListTransacoes.add(transacaoBuscada);
			}
		} catch (IOException ioException) {
			return null;
		}

		return arrayListTransacoes;
	}
	// ----------------------------------------------------------------------------------------------


	// --------------------------------- POSICIONADORES DE PONTEIRO ---------------------------------
	private static long searchPositionContaByCPF(String CPF) {
		long ponteiro = 0;
		Conta contaBuscada = new Conta();
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (true) {
				ponteiro = arquivoContas.getFilePointer();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getCpfCliente().equals(CPF)) {
					arquivoContas.close();
					return ponteiro;
				}
			}
		} catch (IOException ioException) {
			return -1;
		}
	}

	private static long searchPositionClienteByCPF(String CPF) {
		long ponteiro = 0;
		Cliente clienteBuscado = new Cliente();
		RandomAccessFile arquivoClientes = randomAccessFile("clientes.txt");

		try {
			arquivoClientes.seek(0);
			while (true) {
				ponteiro = arquivoClientes.getFilePointer();

				clienteBuscado.setAtivo(arquivoClientes.readBoolean());
				clienteBuscado.setNome(arquivoClientes.readUTF());
				clienteBuscado.setCpf(arquivoClientes.readUTF());
				clienteBuscado.setDataNascimento(arquivoClientes.readUTF());
				clienteBuscado.setEndereco(arquivoClientes.readUTF());
				clienteBuscado.setOcupacao(arquivoClientes.readUTF());
				clienteBuscado.setRenda(arquivoClientes.readDouble());

				if (clienteBuscado.isAtivo() && clienteBuscado.getCpf().equals(CPF)) {
					arquivoClientes.close();
					return ponteiro;
				}
			}
		} catch (IOException ioException) {
			return -1;
		}
	}

	private static long searchPositionContaByNumeroConta(String numeroConta) {
		long ponteiro = 0;
		Conta contaBuscada = new Conta();
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");

		try {
			arquivoContas.seek(0);
			while (true) {
				ponteiro = arquivoContas.getFilePointer();

				contaBuscada.setAtivo(arquivoContas.readBoolean());
				contaBuscada.setCpfCliente(arquivoContas.readUTF());
				contaBuscada.setNumero(arquivoContas.readUTF());
				contaBuscada.setSenha(arquivoContas.readUTF());
				contaBuscada.setTipo(arquivoContas.readUTF());
				contaBuscada.setSaldo(arquivoContas.readDouble());

				if (contaBuscada.isAtivo() && contaBuscada.getNumero().equals(numeroConta)) {
					arquivoContas.close();
					return ponteiro;
				}
			}
		} catch (IOException ioException) {
			return -1;
		}
	}
	// ----------------------------------------------------------------------------------------------


	// ----------------------------------- TRANSAÇÕES FINANCEIRAS -----------------------------------
	public static boolean deposit(String numeroConta, double valorDepositar) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");
		double armazenarSaldoExistente = 0;

		try {
			arquivoContas.seek(searchPositionContaByNumeroConta(numeroConta));
			arquivoContas.readBoolean();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			armazenarSaldoExistente = arquivoContas.readDouble();
			
			arquivoContas.seek(searchPositionContaByNumeroConta(numeroConta));
			arquivoContas.readBoolean();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.writeDouble(armazenarSaldoExistente + valorDepositar);

			return true;
		} catch (IOException ioException) {
			return false;
		}
	}

	public static boolean withdraw(String numeroConta, double valorSacar) {
		RandomAccessFile arquivoContas = randomAccessFile("contas.txt");
		double armazenarSaldoExistente = 0;

		try {
			arquivoContas.seek(searchPositionContaByNumeroConta(numeroConta));
			arquivoContas.readBoolean();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			arquivoContas.readUTF();
			armazenarSaldoExistente = arquivoContas.readDouble();

			if (valorSacar <= armazenarSaldoExistente) {
				arquivoContas.seek(searchPositionContaByNumeroConta(numeroConta));
				arquivoContas.readBoolean();
				arquivoContas.readUTF();
				arquivoContas.readUTF();
				arquivoContas.readUTF();
				arquivoContas.readUTF();
				arquivoContas.writeDouble(armazenarSaldoExistente - valorSacar);
			} else {
				return false;
			}

			return true;
		} catch (IOException ioException) {
			return false;
		}
	}
	// ----------------------------------------------------------------------------------------------
}