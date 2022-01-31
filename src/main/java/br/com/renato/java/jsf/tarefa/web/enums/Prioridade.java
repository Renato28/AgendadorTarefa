package br.com.renato.java.jsf.tarefa.web.enums;

public enum Prioridade {

	ALTA("Alta"), MEDIA("Média"), BAIXA("Baixa");
	
	public String descricao;
	
	private Prioridade(String descricao) {
	
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
