package br.com.casadocodigo.loja.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
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
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class}; //Mapeia a classe de configurações
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; //significa que sera a raiz para o sistema atender
	}
	
    @Override //adiciona um filter para alterar o encoding
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[] {characterEncodingFilter};
    }
    
    //Configuração para o arquivo Multipart
    @Override
    protected void customizeRegistration(Dynamic registration) {
    	registration.setMultipartConfig(new MultipartConfigElement(""));
    }

}
