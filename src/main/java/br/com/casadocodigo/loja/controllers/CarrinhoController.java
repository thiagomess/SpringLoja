package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.model.CarrinhoCompras;
import br.com.casadocodigo.loja.model.CarrinhoItem;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Controller
@RequestMapping("/carrinho")
//A cada request, guarda os itens no CarrinhoCompras.class
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) { //tipoPreco pega o atributo com msm name da pagina
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		
		return modelAndView;
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
		
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remove(produtoId, tipoPreco);

	return new ModelAndView("redirect:/carrinho");
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}

}
