package br.com.projetoweb.projetoweb.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import br.com.projetoweb.projetoweb.model.Lojista;
import br.com.projetoweb.projetoweb.repository.LojistaRepository;
import br.com.projetoweb.projetoweb.web.lojista.LojistaDTO;

@Service
public class LojistaService {

	@Autowired
	private LojistaRepository repository;
	
	public Optional<Lojista> obter(Long id) {
		return this.repository.findById(id);
	}
	
	public List<Lojista> listarTodos() {
		return repository.findAll();
	}
	
	public Page<Lojista> listar(LojistaDTO dto, Integer page, Integer size, String orderBy, String order) {
		
		page = page >= 0 ? page : 0;
		
		Page<Lojista> pagina = this.repository.findAll((Specification<Lojista>) (root, cq, cb) -> {
	            Predicate p = cb.conjunction();
	            
		    if (dto.getNome() != null) {
		    	p = cb.and(p, cb.greaterThanOrEqualTo(root.get("nome"), cb.literal(dto.getNome())));
		    }
		    if (dto.getEmail() != null) {
		    	p = cb.and(p, cb.lessThanOrEqualTo(root.get("email"), cb.literal(dto.getEmail())));
		    }
	            
	            return p;
	        }, PageRequest.of(page, size, Sort.by(Direction.fromString(order), orderBy)));

		return pagina;
	}
	
	@Transactional
    public Lojista inserir(LojistaDTO novoLojista) {

		Lojista lojista = Lojista.builder().lojas(novoLojista.getLojas())
				.nome(novoLojista.getNome()).email(novoLojista.getEmail())
				.senha(novoLojista.getSenha())
		.build();

		return this.repository.save(lojista);
    }
	
	@Transactional
    public Lojista atualizar(LojistaDTO lojistaDTO) {
	
		Lojista lojista = repository.findById(lojistaDTO.getId()).get();
		
		lojista.setNome(lojistaDTO.getNome());
		lojista.setEmail(lojistaDTO.getEmail());
		lojista.setSenha(lojistaDTO.getSenha());
		lojista.setLojas(lojistaDTO.getLojas());
	
		return this.repository.save(lojista);
    }
	
	@Transactional
    public void excluir(Long id) {
		
		if(id != null && id > 0) {
			repository.deleteById(id);
		}
    }
}
