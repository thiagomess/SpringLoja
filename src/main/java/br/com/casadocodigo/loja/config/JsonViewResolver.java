package br.com.casadocodigo.loja.config;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


//Classe para configurar o json
public class JsonViewResolver implements ViewResolver {

	//Lembrando que para isso a aplicação deve ter o "com.fasterxml.jackson.core", no seu pom XML.
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);// foi adicionado para que o Jackson mantenha uma formatação amigável ao retornar o JSON dos nossos produtos.
		
		return jsonView;
	}

}
