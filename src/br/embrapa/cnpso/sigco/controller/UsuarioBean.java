package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.event.RowEditEvent;
import org.springframework.security.core.context.SecurityContextHolder;

import br.embrapa.cnpso.sigco.model.Autorizacao;
import br.embrapa.cnpso.sigco.model.Usuario;

@Stateful
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Usuario usuario;
	private Autorizacao autorizacao;
	private Collection<Usuario> listaUsuarios;
	private List<Usuario> filtroUsuarios;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.usuario = new Usuario();

		Query query = em
				.createQuery("SELECT u FROM Usuario u ORDER BY u.login");
		this.listaUsuarios = query.getResultList();

	}

	public Collection<Usuario> getListaUsuarios() {
		return this.listaUsuarios;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	public Autorizacao getAutorizacao() {
		return this.autorizacao;
	}

	public void setUsuario(Usuario usuario) {
		System.out.println("Passei por aqui.");
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public List<Usuario> getFiltroUsuarios() {
		return this.filtroUsuarios;
	}

	public void setFiltroUsuarios(List<Usuario> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}

	public String validaAuth() {
		String login = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		return login;

	}

	public void salvar(Usuario usr) {

		try {
			this.em.persist(usr);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(Usuario usr) {

		try {
			Usuario usuario = this.em.find(Usuario.class, usr.getLogin());
			this.em.remove(usuario);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.usuario = (Usuario) event.getObject();

		FacesMessage msg = new FacesMessage("Usuario Editado",
				usuario.getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(usuario);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Usuario Cancelado",
				((Usuario) event.getObject()).getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}