package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos."),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Ocorreu um erro interno inesperado no sistema."),
	PARAMETRO_INVALIDO("/paramentro-invalido", "O parametro passado esta invalido."),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel."),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontada", "Recurso não encontrado."),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio.");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
		
	}
	
	
}
