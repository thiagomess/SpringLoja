package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.Usuario;

// não é suficiente para configurar a autenticação dos usuários. Aconteque que o método userDetailsService espera receber um objeto que implemente uma interface 
//com este mesmo nome. Faremos então classe UsuarioDAO implementar a interface e adicionar à classe os método que precisam ser implementados.
@Repository
public class UsuarioDao implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public void register(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario loadUserByUsername(String login) {

		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.login = :login", Usuario.class)
				.setParameter("login", login).getResultList();

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + login + " nao encontrado");
		}

		return usuarios.get(0);

	}
	
	public List<Usuario> findAllUsuarios(){
		  CriteriaBuilder cb = manager.getCriteriaBuilder();
	        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
	        Root<Usuario> usuario = criteria.from(Usuario.class);
	        
	        criteria.select(usuario).orderBy(cb.asc(usuario.get("nome")));
			return manager.createQuery(criteria).getResultList();
	}
	
/*	insert into Usuario (email, nome, senha) values ('admin@casadocodigo.com.br', 'Administrador', '$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq')

	//senha 123456
	insert into Usuario (id, login,nome, senha) values (1,'admin@casadocodigo.com.br', 'Administrador', '$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq')

	insert into Usuario_Role(Usuario_id, roles_nome) values (1, 'ROLE_ADMIN')	
	
	site para criar senhas: https://www.dailycred.com/article/bcrypt-calculator
	
	*/

}
