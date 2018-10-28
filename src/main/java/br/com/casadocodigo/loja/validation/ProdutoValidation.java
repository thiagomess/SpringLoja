package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.model.Produto;

public class ProdutoValidation implements Validator {

	@Override //Verifica se o parametro é realmente um produto
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override //Realiza as validacoes para verificar se o campo esta Vazio
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo","field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao","field.required");
		
		Produto produto = (Produto) target;
		if (produto.getPaginas()==null || produto.getPaginas() <=0) {
			errors.rejectValue("paginas", "field.required");
		}
	}
}
