package br.com.projetoweb.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.projetoweb.projetoweb.model.Lojista;

public interface LojistaRepository extends JpaRepository<Lojista, Long>, JpaSpecificationExecutor<Lojista>{

}
