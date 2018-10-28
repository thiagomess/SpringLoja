package br.com.casadocodigo.loja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;

@EnableWebMvc // recurso de Web MVC do SpringMVC
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDao.class}) //mapeia onde estao os controllers
public class AppWebConfiguration {
	
	@Bean  //Para o Spring gerencia
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); //seta a pasta onde estarao as views
		resolver.setSuffix(".jsp"); //seta o sufixo das JSP
		
		return resolver;
		
	}

}
