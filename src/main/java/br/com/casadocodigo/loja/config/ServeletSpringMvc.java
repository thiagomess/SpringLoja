package br.com.casadocodigo.loja.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//Classe de servlet do spring

public class ServeletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class}; //Mapeia a classe de configurações
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; //significa que sera a raiz para o sistema atender
	}

}
