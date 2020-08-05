package com.johnatanlima.bancocapgemini.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.johnatanlima.bancocapgemini.domain.Agencia;
import com.johnatanlima.bancocapgemini.domain.Cidade;
import com.johnatanlima.bancocapgemini.domain.Cliente;
import com.johnatanlima.bancocapgemini.domain.ContaCorrente;
import com.johnatanlima.bancocapgemini.domain.Endereco;
import com.johnatanlima.bancocapgemini.domain.Estado;
import com.johnatanlima.bancocapgemini.domain.enums.EstadoContaCorrente;
import com.johnatanlima.bancocapgemini.domain.enums.Perfil;
import com.johnatanlima.bancocapgemini.repositories.AgenciaRepository;
import com.johnatanlima.bancocapgemini.repositories.CidadeRepository;
import com.johnatanlima.bancocapgemini.repositories.ClienteRepository;
import com.johnatanlima.bancocapgemini.repositories.ContaCorrenteRepository;
import com.johnatanlima.bancocapgemini.repositories.EnderecoRepository;
import com.johnatanlima.bancocapgemini.repositories.EstadoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private AgenciaRepository agenciaRepository;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		// Clientes
		Cliente cl1 = new Cliente(null, "johnatanlima00@gmail.com", "Johnatan da Silva Lima", pe.encode("123456"), "90669730050");
		cl1.addPerfil(Perfil.ADMIN);
		Cliente cl2 = new Cliente(null, "contato@johnatanlima.com.br", "Johnatan Lima", pe.encode("123456"), "08257534064");

		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		
		// Agências e Contas
		Agencia ag1 = new Agencia(null, "Agencia do Caminho de Areia");
		Agencia ag2 = new Agencia(null, "Agencia da Pituba");
		
		ContaCorrente cc1 = new ContaCorrente(null, EstadoContaCorrente.ABERTA, 100.00, ag1, cl1);
		ContaCorrente cc2 = new ContaCorrente(null, EstadoContaCorrente.ENCERRADA, 20.00, ag1, cl2);
		ContaCorrente cc3 = new ContaCorrente(null, EstadoContaCorrente.ENCERRADA, 300.00, ag2, cl1);
		ContaCorrente cc4 = new ContaCorrente(null, EstadoContaCorrente.ABERTA, 00.00, ag2, cl2);
		
		ag1.getContas().addAll(Arrays.asList(cc1, cc2));
		ag2.getContas().addAll(Arrays.asList(cc3, cc4));
		
		cl1.getContas().addAll(Arrays.asList(cc1, cc3));
		cl2.getContas().addAll(Arrays.asList(cc2, cc4));
		
		agenciaRepository.saveAll(Arrays.asList(ag1, ag2));
		contaCorrenteRepository.saveAll(Arrays.asList(cc1, cc2, cc3, cc4));
		
		
		// Estados e Cidades
		Estado est1 = new Estado(null, "Bahia");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Salvador", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		// Endereços
		Endereco e1 = new Endereco(null, "Av. Caminho de Areia", "300", "Apto 303", "Caminho de Areia", "38220834", c1, cl1);
		Endereco e2 = new Endereco(null, "Av. Paralela", "Prédio A", "Sala 100", "CAB", "38777012", c1, cl2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1));
		cl2.getEnderecos().addAll(Arrays.asList(e2));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
