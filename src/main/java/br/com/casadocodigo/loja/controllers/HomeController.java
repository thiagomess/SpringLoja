package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.model.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDao dao;
	
	@RequestMapping("/")
	//Mapeando o cache para este metodo, dando um value, para poder remover com o @CacheEvict
	@Cacheable(value="produtosHome") 
	public ModelAndView Home() {
		
		List<Produto> produtos = dao.lista();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
		
	}
	
	

}
