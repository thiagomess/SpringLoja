package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.config.AppWebConfiguration;
import br.com.casadocodigo.loja.config.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.config.JPAConfiguration;
import br.com.casadocodigo.loja.config.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) //Seta que ira usar o frame de spring-test
@WebAppConfiguration //a presença da anotação @WebAppConfiguration que faz o carregamento das demais configurações de MVC do Spring
//@ContextConfiguration em vez de carregarmos a classe ProdutoDAO, carregamos a classe que tem todas as configurações de MVC da aplicação, AppWebConfiguration
@ContextConfiguration(classes= {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class}) //Adiciona a classe de configurações do Spring onde determinamos os acessos
@ActiveProfiles("test") //Informa para o spring que quer utilizar o banco de dados de Test
public class ProdutosControllersTeste {
	
	@Autowired
	private WebApplicationContext wac; //O Spring já conhece o contexto da aplicação, por isso o atributo WebApplicationContext é anotado com @Autowired
	private MockMvc mockMvc; // Objeto MockMvc será o objeto que fará as requisições para os controllers da nossa aplicação.
	
	@Autowired
	private Filter springSecurityFilterChain; //Para testar o acesso de permisssao, é necessario injetar o Filter e adiciona-lo no setup()
	
	@Before
	public void setup() {
//		Para a criação do objeto MockMvc, definiremos um método que será executado antes dos testes e instanciaremos o objeto usando a classe MockMvcBuilders. 
//		Iremos fornecer para o método, que se chamará setup, o contexto webAppContextSetup. 
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void deveRetornarParaHomeComOsLivros() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")) //o método perform do objeto mockMvc para simular uma requisição
		.andExpect(MockMvcResultMatchers.model().attributeExists("produtos")) //o método model para que consigamos obter informações sobre o objeto retornado pela requisição e neste objeto, verificaremos a existencia do atributo produtos utilizando o método attributeExists.
		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp")); //Com a requisição feita, nos resta verificar 
		//o resultado da mesma por meio do método andExpect do objeto mockMvc que receberá o resultado do método forwardedUrl da classe MockMvcResultMatchers no qual verificará se foi feito um redirecionamento no servidor para a view localizada em WEB-INF/views/home.jsp;
	}




	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
				.with(SecurityMockMvcRequestPostProcessors.user("user@casadocodigo.com.br").password("12345").roles("USER")))// Para testar uma tentativa de autenticação, precisamos que o Mock MVC faça a requisição um Post Processor. Ou seja, um processo de POST antes de 
		 																													//executar o GET da página, passando neste Post Processor os dados de autenticação do usuário. 
			.andExpect(MockMvcResultMatchers.status().is(403)); //Esperando o status 403 - Forbidden
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
				.with(SecurityMockMvcRequestPostProcessors.user("user@casadocodigo.com.br").password("12345").roles("ADMIN"))) //Role cadastrada que permite o acesso
			.andExpect(MockMvcResultMatchers.status().is(200)); //Esperando o status 200 - Sucess
	}



}
