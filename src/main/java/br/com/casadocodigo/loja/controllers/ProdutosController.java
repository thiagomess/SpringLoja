package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;

	@RequestMapping("produtos/form")
	public ModelAndView form() {
		//Cria o objeto pra mandar pra tela e passa a pagina no construtor
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping("produtos")
	public String grava(Produto produto ) {
		
		System.out.println(produto);
		produtoDao.gravar(produto);
		
		return "produtos/ok";

		
	}
}
