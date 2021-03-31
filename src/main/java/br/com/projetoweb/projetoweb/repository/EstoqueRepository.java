package br.com.projetoweb.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.projetoweb.projetoweb.model.ProdutoEstoque;

public interface EstoqueRepository extends JpaRepository<ProdutoEstoque, Long>, JpaSpecificationExecutor<ProdutoEstoque>{

}
