package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.daos.UsuarioDao;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.Role;
import br.com.casadocodigo.loja.model.Usuario;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDao dao;

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping("/")
	// Mapeando o cache para este metodo, dando um value, para poder remover com o
	// @CacheEvict
	@Cacheable(value = "produtosHome")
	public ModelAndView Home() {

		List<Produto> produtos = dao.lista();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);

		return modelAndView;

	}

	@Transactional
	@ResponseBody
	@RequestMapping("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn")
	public String populaBanco() {

		Usuario user = new Usuario();
		user.setNome("Admin");
		user.setLogin("admin@casadocodigo.com.br");
		user.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

		usuarioDao.register(user);
		return "Url Mágica executada";

	}

}
