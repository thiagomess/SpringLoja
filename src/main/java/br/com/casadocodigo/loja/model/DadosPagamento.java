package br.com.casadocodigo.loja.model;

import java.math.BigDecimal;

//Classe para o spring converter o valor em um atributo com name VALUE, dessa forma conseguirá gerar o JSON com a biblioteca do Jackson automaticamente.
public class DadosPagamento {

	private BigDecimal value;

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public DadosPagamento() {
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
