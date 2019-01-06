package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder //Para o produto controller poder reconhecer o ProdutoValidation
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		//Cria o objeto pra mandar pra tela e passa a pagina no construtor
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="produtosHome", allEntries=true) //Ao gravar novo livro, essa anotação irá remover o cache para ser salvar um novo
	public ModelAndView grava(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes ) {
		
		if (result.hasErrors()) {
			return form(produto);
		}
		
		//salva na pasta arquivos-sumario
		String path = fileSaver.write("arquivos-sumario", sumario);
		
		produto.setSumarioPath(path);
		produtoDao.gravar(produto);
		//Adiciona a mensagem no flash para ser exibida em outra pagina
		redirectAttributes.addFlashAttribute("sucesso", "Livro cadastrado com sucesso!");
		//efetua o redirect para a outra pagina alterando a URL para a certa
		// //realizamos um redirect após um formulário com POST.
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping("/detalhe/{id}") //recebenddo o parametro e linkando o parametro com o @
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);
		
		return modelAndView;
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDao.lista();
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
		
	}
}
