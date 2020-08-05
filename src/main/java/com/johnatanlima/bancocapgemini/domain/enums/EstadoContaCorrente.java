package com.johnatanlima.bancocapgemini.domain.enums;

public enum EstadoContaCorrente {

	ABERTA(1, "Aberta"),
	ENCERRADA(2, "Encerrada");
	
	private int cod;
	private String descricao;
	
	private EstadoContaCorrente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoContaCorrente toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (EstadoContaCorrente x : EstadoContaCorrente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
