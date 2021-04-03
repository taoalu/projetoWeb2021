package br.com.projetoweb.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.projetoweb.projetoweb.model.Venda;

public interface VendaRepository  extends JpaRepository<Venda, Long>, JpaSpecificationExecutor<Venda>{

}
