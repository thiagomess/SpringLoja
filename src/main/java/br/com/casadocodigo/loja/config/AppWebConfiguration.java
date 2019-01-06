package br.com.casadocodigo.loja.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.model.CarrinhoCompras;

@EnableWebMvc // recurso de Web MVC do SpringMVC
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDao.class, FileSaver.class, CarrinhoCompras.class}) //mapeia onde estao os controllers
@EnableCaching //Habilitando o CACHE
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
	
		
	//Configuração para iniciar o RestTemplate
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	//Configurando o cache para a aplicação
	@Bean
	public CacheManager cacheManager() {
		//É utilizada o cache do Guava, pois a biblioteca propria do Spring nao é recomendada segunda a doc.
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		builder.maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
		GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
		guavaCacheManager.setCacheBuilder(builder);
		return guavaCacheManager;
				
		//return new ConcurrentMapCacheManager();//usada só para testes
		
	}

}
