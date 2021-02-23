package br.com.projetoweb.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.projetoweb.projetoweb.model.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long>, JpaSpecificationExecutor<Loja>{

}
