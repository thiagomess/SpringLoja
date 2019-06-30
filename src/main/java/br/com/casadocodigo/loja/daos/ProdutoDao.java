package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Repository //Anotação para o @Autowired poder encontra-lo
@Transactional //O spring ira fazer a transacao
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void gravar(Produto produto) {
		em.persist(produto);
	}

	public List<Produto> lista() {
		//Quando temos problemas de LazyInitializationException devemos usar o join fetch e o distinct
		String jpql ="Select distinct(p) from Produto p join fetch p.precos";
		
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return em.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco) {
		
		TypedQuery<BigDecimal> createQuery = em.createQuery("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipo", BigDecimal.class);
		return createQuery.setParameter("tipo", tipoPreco).getSingleResult();
		
	}
}
