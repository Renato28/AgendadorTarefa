package br.com.renato.java.jsf.tarefa.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.renato.java.jsf.tarefa.web.dao.ResponsavelDao;
import br.com.renato.java.jsf.tarefa.web.dao.TarefaDao;
import br.com.renato.java.jsf.tarefa.web.enums.Prioridade;
import br.com.renato.java.jsf.tarefa.web.enums.Situacao;
import br.com.renato.java.jsf.tarefa.web.model.Responsavel;
import br.com.renato.java.jsf.tarefa.web.model.Tarefa;

@ManagedBean
@ViewScoped
public class TarefaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa;
	private Responsavel responsavel;
	private TarefaDao tarefaDao;
	private List<Tarefa> tarefas;
	private List<Responsavel> responsaveis;
	private ResponsavelDao responsavelDao;

	@PostConstruct
	public void init() {
		tarefaDao = new TarefaDao();
		tarefa = new Tarefa();
		responsavel = new Responsavel();
		responsavelDao = new ResponsavelDao();

		try {

			tarefas = tarefaDao.listarTarefas();
			responsaveis = responsavelDao.listarResponsaveis();

		} catch (Exception e) {

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public String inserir() {
		try {
			tarefaDao.inserir(tarefa);

			tarefas = tarefaDao.listarTarefas();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Tarefa adicionada com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}

	public String atualizar() {
		try {
			tarefaDao.atualizar(tarefa);

			tarefas = tarefaDao.listarTarefas();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Tarefa atualizada com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}

	public String excluir() {
		try {
			tarefaDao.excluir(tarefa.getId());

			tarefas = tarefaDao.listarTarefas();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Tarefa excluida com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}

	public void selecionar() {
		try {
			tarefa = tarefaDao.selecionar(tarefa.getId());

			if (tarefa == null || tarefa.getId() == 0) {
				tarefa = new Tarefa();

				throw new Exception("Tarefa não encontrada.");
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public Prioridade[] getPrioridades() {
		return Prioridade.values();
	}

	public Situacao[] getSituacao() {
		return Situacao.values();
	}
}
