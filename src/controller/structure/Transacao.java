package controller.structure;

public class Transacao {
	private String tipo;
	private String numeroContaEntrada;
	private String numeroContaSaida;
	private String data;
	private double valor;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumeroContaEntrada() {
		return numeroContaEntrada;
	}

	public void setNumeroContaEntrada(String numeroContaEntrada) {
		this.numeroContaEntrada = numeroContaEntrada;
	}

	public String getNumeroContaSaida() {
		return numeroContaSaida;
	}

	public void setNumeroContaSaida(String numeroContaSaida) {
		this.numeroContaSaida = numeroContaSaida;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}