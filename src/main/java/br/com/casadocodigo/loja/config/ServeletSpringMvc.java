package br.com.casadocodigo.loja.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//Classe de servlet do spring
public class ServeletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//Sobe a configuração de segurança quando o sistema subir
		return new Class[] {SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class}; ///por conta do login, tem que passar o JPa para ca
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
//		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class}; //Mapeia a classe de configurações
		return new Class[] {};
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
    
    //Configuração para adicionar o profile de qual banco de dados que ficará ativo ao startar o servidor
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	super.onStartup(servletContext);
    	servletContext.addListener(RequestContextListener.class);
    	servletContext.setInitParameter("spring.profiles.active", "dev");
    }

}
