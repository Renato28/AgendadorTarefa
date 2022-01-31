package br.com.renato.java.jsf.tarefa.web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.renato.java.jsf.tarefa.web.bean.JpaResourceBean;
import br.com.renato.java.jsf.tarefa.web.model.Responsavel;
import br.com.renato.java.jsf.tarefa.web.model.Tarefa;

public class TarefaDao {

	@SuppressWarnings("unchecked")
	public List<Tarefa> listarTarefas() throws Exception {

		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		List<Tarefa> tarefas = null;

		try {

			tarefas = em.createQuery("from Tarefa").getResultList();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			em.close();
		}

		return tarefas;
	}
	
	@SuppressWarnings("unchecked")
	public List<Responsavel> listarResponsavel() throws Exception {

		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		List<Responsavel> responsaveis = null;

		try {

			responsaveis = em.createQuery("from Responsavel").getResultList();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			em.close();
		}

		return responsaveis;
	}

	public void inserir(Tarefa tarefa) throws Exception {

		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();

		try {

			em.getTransaction().begin();
			em.persist(tarefa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();

			throw new Exception(e.getMessage());

		} finally {
			em.close();
		}
	}

	public void atualizar(Tarefa tarefa) throws Exception {

		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();

		try {

			em.getTransaction().begin();
			em.merge(tarefa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();

			throw new Exception(e);
		} finally {

			em.close();
		}
	}
	
	public void excluir(long id) throws Exception {
		
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		
		try {
			
			em.getTransaction().begin();
			Tarefa tarefa = em.find(Tarefa.class, id);
			em.remove(tarefa);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			
			throw new Exception(e.getMessage());
		} finally {
			
			em.close();
		}
	}
	
	public Tarefa selecionar(long id) throws Exception {
		
		Tarefa tarefa;
		
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		
		try {
			
			tarefa = em.find(Tarefa.class, id);
		} finally {
			
			em.close();
		}
		
		return tarefa;
	}
}
