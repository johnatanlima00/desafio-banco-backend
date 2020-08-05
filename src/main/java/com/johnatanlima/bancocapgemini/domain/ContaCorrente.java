package com.johnatanlima.bancocapgemini.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.johnatanlima.bancocapgemini.domain.enums.EstadoContaCorrente;

@Entity
public class ContaCorrente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer estado;
	private double saldo;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="agencia_id")
	private Agencia agencia;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	
	public ContaCorrente() {
		
	}

	public ContaCorrente(Integer id, EstadoContaCorrente estado, double saldo, Agencia agencia, Cliente cliente) {
		super();
		this.id = id;
		this.estado = (estado==null) ? null : estado.getCod();
		this.saldo = saldo;
		this.agencia = agencia;
		this.cliente = cliente;
	}
	
	public ContaCorrente(Integer id, EstadoContaCorrente estado, double saldo) {
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

	public EstadoContaCorrente getEstado() {
		return EstadoContaCorrente.toEnum(estado);
	}

	public void setEstado(EstadoContaCorrente estado) {
		this.estado = estado.getCod();
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
