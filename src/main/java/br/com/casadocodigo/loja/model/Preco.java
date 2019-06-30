package br.com.casadocodigo.loja.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable //Mapeia o objeto com o produto
public class Preco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7669988565669171801L;
	
	private BigDecimal valor;
	private TipoPreco tipo;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return this.tipo.name() + " - " +this.valor;
	}
	
	

}
