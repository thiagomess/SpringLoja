package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.config.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.config.JPAConfiguration;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class) //Seta que ira usar o frame de spring-test
@ContextConfiguration(classes= {JPAConfiguration.class, ProdutoDao.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test") //Informa para o spring que quer utilizar o banco de dados de Test
public class ProdutoDaoTeste {

	@Autowired
//   @Qualifier("produtoDao") Ao usar interface, deve-se usar o  @Qualifier = https://cursos.alura.com.br/forum/topico-teste-de-dao-que-implementa-uma-interface-32274
	private ProdutoDao produtoDao;
	
	
	@Test
	@Transactional
	public void deveSomarTodosPrecosPorTipoLivro() {
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();
		
		livrosImpressos.stream().forEach(produtoDao::gravar);
		livrosEbook.stream().forEach(produtoDao::gravar);
		
		BigDecimal somaImpresso = produtoDao.somaPrecosPorTipo(TipoPreco.IMPRESSO);
		BigDecimal somaEbook = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
		
		Assert.assertEquals(new BigDecimal(40).setScale(2), somaImpresso);
	}
}
