package br.com.projetoweb.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.projetoweb.projetoweb.model.Artesao;

public interface ArtesaoRepository extends JpaRepository<Artesao, Long>, JpaSpecificationExecutor<Artesao>{

}
