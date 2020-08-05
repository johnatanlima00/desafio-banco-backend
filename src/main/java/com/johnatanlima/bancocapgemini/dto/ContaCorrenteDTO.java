package com.johnatanlima.bancocapgemini.dto;

import java.io.Serializable;

import com.johnatanlima.bancocapgemini.domain.enums.EstadoContaCorrente;

public class ContaCorrenteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer estado;
	private double saldo;
	
	public ContaCorrenteDTO() {
		
	}

	public ContaCorrenteDTO(Integer id, EstadoContaCorrente estado, double saldo) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public EstadoContaCorrente getEstado() {
		return EstadoContaCorrente.toEnum(estado);
	}

	public void setEstado(EstadoContaCorrente estado) {
		this.estado = estado.getCod();
	}
	
}
