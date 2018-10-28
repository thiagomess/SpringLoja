package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Controller
@RequestMapping("produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;

	@RequestMapping("/form")
	public ModelAndView form() {
		//Cria o objeto pra mandar pra tela e passa a pagina no construtor
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String grava(Produto produto ) {
		System.out.println(produto);
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDao.lista();
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
		
	}
}
