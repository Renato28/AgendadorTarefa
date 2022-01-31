package br.com.renato.java.jsf.tarefa.web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.renato.java.jsf.tarefa.web.bean.JpaResourceBean;
import br.com.renato.java.jsf.tarefa.web.model.Responsavel;

public class ResponsavelDao {

	 @SuppressWarnings("unchecked")
	public List<Responsavel> listarResponsaveis() throws Exception {
	        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
	        List<Responsavel> responsaveis = null;

	        try {
	            responsaveis = em.createQuery("from Responsavel").getResultList();
	        } catch (Exception e) {
	            throw new Exception(e);
	        } finally {
	            em.close();
	        }

	        return responsaveis;
	    }
}
