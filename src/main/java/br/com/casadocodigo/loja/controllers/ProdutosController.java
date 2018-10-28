package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String grava(Produto produto, RedirectAttributes redirectAttributes ) {
		System.out.println(produto);
		produtoDao.gravar(produto);
		//Adiciona a mensagem no flash para ser exibida em outra pagina
		redirectAttributes.addFlashAttribute("sucesso", "Livro cadastrado com sucesso!");
		//efetua o redirect para a outra pagina alterando a URL para a certa
		// //realizamos um redirect após um formulário com POST.
		return "redirect:produtos";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDao.lista();
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
		
	}
}
