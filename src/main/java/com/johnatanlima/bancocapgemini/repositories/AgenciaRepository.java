package com.johnatanlima.bancocapgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnatanlima.bancocapgemini.domain.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

}
