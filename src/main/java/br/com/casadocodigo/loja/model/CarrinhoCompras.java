package br.com.casadocodigo.loja.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Essa classe serve para verificar se o produto existe no carrinho, 
 * caso exista e esteja adicionando um novo, ele soma mais um, caso na exista, adiciona ao MAP
 * @author Thiago
 *
 */
@Component  
//Guarda a sessao dos itens que foram adicionados ao carrinho
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	private int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		//Varre a collections retornando a quantidade
		 Integer reduce = itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
		 return reduce;
	}
}
