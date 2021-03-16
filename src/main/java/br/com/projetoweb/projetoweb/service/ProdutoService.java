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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoweb.projetoweb.model.Artesao;
import br.com.projetoweb.projetoweb.model.Produto;
import br.com.projetoweb.projetoweb.repository.ArtesaoRepository;
import br.com.projetoweb.projetoweb.repository.ProdutoRepository;
import br.com.projetoweb.projetoweb.web.produto.ProdutoDTO;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ArtesaoRepository artesaoRepository;
	
	public Optional<Produto> obter(Long id) {
		return this.repository.findById(id);
	}
	
	public List<Produto> listarTodos() {
		return repository.findAll();
	}
	
	public Page<Produto> listar(ProdutoDTO dto, Integer page, Integer size, String orderBy, String order) {
		
		page = page >= 0 ? page : 0;
		
		Page<Produto> pagina = this.repository.findAll((Specification<Produto>) (root, cq, cb) -> {
	            Predicate p = cb.conjunction();
	            
		    if (dto.getDescricao() != null) {
		    	p = cb.and(p, cb.greaterThanOrEqualTo(root.get("descricao"), cb.literal(dto.getDescricao())));
		    }
		    if (dto.getIdArtesao() != null) {
		    	p = cb.and(p, cb.lessThanOrEqualTo(root.get("artesao.id"), cb.literal(dto.getIdArtesao())));
		    }
		    if (dto.getPreco() != null) {
		    	p = cb.and(p, cb.lessThanOrEqualTo(root.get("preco"), cb.literal(dto.getPreco())));
		    }
	            
	            return p;
	        }, PageRequest.of(page, size, Sort.by(Direction.fromString(order), orderBy)));

		return pagina;
	}
	
	@Transactional
    public Produto inserir(ProdutoDTO novoProduto) {

		Artesao artesao = artesaoRepository.findById(novoProduto.getIdArtesao()).get();
		
		Produto prod = Produto.builder().descricao(novoProduto.getDescricao()).preco(novoProduto.getPreco())
				.artesao(artesao)
		.build();

		return this.repository.save(prod);
    }
	
	@Transactional
    public Produto atualizar(ProdutoDTO prodDTO) {
	
		Produto produto = repository.findById(prodDTO.getId()).get();
		
		produto.setDescricao(prodDTO.getDescricao());
		produto.setPreco(prodDTO.getPreco());		
	
		return this.repository.save(produto);
    }
	
	@Transactional
    public void excluir(Long id) {
		
		if(id != null && id > 0) {
			repository.deleteById(id);
		}
    }
}
