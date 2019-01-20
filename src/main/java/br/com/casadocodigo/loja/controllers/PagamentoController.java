package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.model.CarrinhoCompras;
import br.com.casadocodigo.loja.model.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {

		// Utilizando o metodo Callable, o processo fica de forma assincrona, sendo
		// necessario utilizar uma classse anonima.
		// Usando esse return, esta sendo usado a classe anonima do java 8
		return () -> {

			String uri = "http://book-payment.herokuapp.com/payment";

			try {
				// O rest template precisa ser inicializado no AppWebConfiguration
				// Ele deve enviar a uri, um json com o value e irá receber uma string de
				// resposta.
				// Para a conversao para o JSON, foi utilizado a biblioteca do JACKSON no pom e
				// criado a classe DadosPagamento com o atributo nomeado "value"
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotalCarrinho()),
						String.class);

				model.addFlashAttribute("sucesso", response); // resposta do rest
				System.out.println(response);
				return new ModelAndView("redirect:/");

			} catch (HttpClientErrorException e) {
				// em caso de falha, seta a mensagem na tela.
				e.printStackTrace();

				model.addFlashAttribute("falha", "Pagamento não realizado, Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}

		};

	}
}
