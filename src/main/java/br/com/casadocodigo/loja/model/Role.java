package br.com.casadocodigo.loja.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;


//Para resolvermos o problema, basta fazer com que a classe Role implemente a interface e adicione seus métodos. 
//Esta interface tem apenas um método chamado getAuthority, no qual retornaremos o atributo nome da classe Role.
@Entity
public class Role  implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String nome;
	public Role() {
	}

	public Role(String string) {
		this.nome = string;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}
}
