package br.com.casadocodigo.loja.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.model.CarrinhoCompras;

@EnableWebMvc // recurso de Web MVC do SpringMVC
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDao.class, FileSaver.class, CarrinhoCompras.class}) //mapeia onde estao os controllers
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean  //Para o Spring gerencia
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); //seta a pasta onde estarao as views
		resolver.setSuffix(".jsp"); //seta o sufixo das JSP
		
		resolver.setExposedContextBeanNames("carrinhoCompras"); //permite acesso aos atributos pela JSP
		
		return resolver;
		
	}
	
	//Configuração para encontrar o arquivo message.properties
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
		message.setBasename("/WEB-INF/messages");
		message.setDefaultEncoding("UTF-8");
		message.setCacheSeconds(1);
		return message;
	}
	
	//Desse modo nao precisamos mais definir padrao de DATA
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	//Configuração para o arquivo Multipart, continuação da configuração em class ServeletSpringMvc
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
//	 Por padrão, o Spring MVC nega o acesso à pasta resources. Consequentemente, o Tomcat não pode 
//	 carregar os arquivos CSS (e a página fica sem design). Para liberar o acesso deve ser feito esse metodo
//	e a classe deve estender WebMvcConfigurerAdapter
		@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}

}
