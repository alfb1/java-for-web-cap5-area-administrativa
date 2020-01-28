package br.com.javaparaweb.financeiro.usuario;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAOHibernate implements UsuarioDAO {
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Usuario usuario) {
       this.session.save(usuario);
	}

	public void atualizar(Usuario usuario) {
       this.session.update(usuario);
	}
	
	public void excluir(Usuario usuario) {
       this.excluir(usuario);
	}
	
	public Usuario carregar(Integer codigo) {
       return (Usuario) this.session.get(Usuario.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).list();
	}
	
	public Usuario buscarPorLogin(String login) {
		// uma selecao no objeto Usuario - hql (Hibernate query language)
		String hql = "select u from Usuario u where u.login = :login";
		// utilizando o session interno para gerar a consulta
		Query query = this.session.createQuery(hql);
		// setando o parametro login do hql para o login passado no metodo
		query.setString("login", login);
		// retornando apenas um resultado na consulta
		return (Usuario) query.uniqueResult();
	}



}
