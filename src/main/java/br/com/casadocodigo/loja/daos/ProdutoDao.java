package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.model.Produto;

@Repository //Anotação para o @Autowired poder encontra-lo
@Transactional //O spring ira fazer a transacao
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void gravar(Produto produto) {
		em.persist(produto);
	}

	public List<Produto> lista() {
		String jpql ="Select p from Produto p";
		
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return em.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}
}
