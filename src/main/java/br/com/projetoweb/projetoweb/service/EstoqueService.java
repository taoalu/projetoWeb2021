package br.com.projetoweb.projetoweb.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoweb.projetoweb.model.Produto;
import br.com.projetoweb.projetoweb.model.ProdutoEstoque;
import br.com.projetoweb.projetoweb.repository.EstoqueRepository;
import br.com.projetoweb.projetoweb.repository.ProdutoRepository;
import br.com.projetoweb.projetoweb.web.estoque.ProdutoEstoqueDTO;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<ProdutoEstoque> obter(Long id) {
		return this.repository.findById(id);
	}
	
	public List<ProdutoEstoque> listarTodos() {
		return repository.findAll();
	}
	
	public List<ProdutoEstoque> listar(ProdutoEstoqueDTO dto) {
		
		List<ProdutoEstoque> pagina = this.repository.findAll((Specification<ProdutoEstoque>) (root, cq, cb) -> {
	            Predicate p = cb.conjunction();
	            
		    if (dto.getIdProduto() != null) {
		    	p = cb.and(p, cb.greaterThanOrEqualTo(root.get("produto.id"), cb.literal(dto.getIdProduto())));
		    }
		    if (dto.getQuantidade() != null) {
		    	p = cb.and(p, cb.lessThanOrEqualTo(root.get("quantidade"), cb.literal(dto.getQuantidade())));
		    }
	            
	            return p;
	        });

		return pagina;
	}
	
	@Transactional
    public ProdutoEstoque inserir(ProdutoEstoqueDTO novoProdutoEstoque) {

		Produto prod = produtoRepository.findById(novoProdutoEstoque.getIdProduto()).get();
		
		ProdutoEstoque estoque = ProdutoEstoque.builder().dataReposicao(novoProdutoEstoque.getDataReposicao())
				.produto(prod).quantidade(novoProdutoEstoque.getQuantidade())
		.build();

		return this.repository.save(estoque);
    }
	
	@Transactional
    public ProdutoEstoque atualizar(ProdutoEstoqueDTO prodDTO) {
	
		ProdutoEstoque estoque = repository.findById(prodDTO.getId()).get();
		
		estoque.setQuantidade(prodDTO.getQuantidade());
	
		return this.repository.save(estoque);
    }
	
	@Transactional
    public void excluir(Long id) {
		
		if(id != null && id > 0) {
			repository.deleteById(id);
		}
    }
	
}
