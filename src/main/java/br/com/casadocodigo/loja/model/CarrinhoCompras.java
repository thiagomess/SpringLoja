package br.com.casadocodigo.loja.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
//criará um proxy envolvendo o objeto alvo (TARGET_CLASS) afim de possibilitar que as informações possam ser transitadas de um 
//escopo para o outro. A anotação com a modificação proposta fica da seguinte forma.

public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 726811311385938764L;
	/**
	 * 
	 */
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public Integer getQuantidade(CarrinhoItem item) {
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

	//Pega a quantidade de itens que tem no MAP
	public Collection<CarrinhoItem> getItens() {
		 Set<CarrinhoItem> keySet = itens.keySet();
		 return keySet;
	}
	
	//Devolve o valor total de cada item de acordo com sua quantidade
	public BigDecimal getTotal(CarrinhoItem item) {
		 BigDecimal total = item.getTotal(getQuantidade(item));
		 return total;
	}
	
	//Devolve o valor total do carrinho para o pagamento
	public BigDecimal getTotalCarrinho() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remove(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto, tipoPreco));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
