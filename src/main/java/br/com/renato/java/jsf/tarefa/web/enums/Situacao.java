package br.com.renato.java.jsf.tarefa.web.enums;

public enum Situacao {

	ANDAMENTO("Andamento"), CONCLUIDA("Concluida");
	
	public String descricao;
	
	private Situacao(String descricao) {
		
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
