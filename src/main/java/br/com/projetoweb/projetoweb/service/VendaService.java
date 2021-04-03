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

import br.com.projetoweb.projetoweb.model.ProdutoEstoque;
import br.com.projetoweb.projetoweb.model.Venda;
import br.com.projetoweb.projetoweb.repository.EstoqueRepository;
import br.com.projetoweb.projetoweb.repository.VendaRepository;
import br.com.projetoweb.projetoweb.web.venda.VendaDTO;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private EstoqueRepository produtoEstoqueRepository;
	
	public Optional<Venda> obter(Long id) {
		return this.repository.findById(id);
	}
	
	public List<Venda> listarTodos() {
		return repository.findAll();
	}
	
	public Page<Venda> listar(VendaDTO dto, Integer page, Integer size, String orderBy, String order) {
		
		page = page >= 0 ? page : 0;
		
		Page<Venda> pagina = this.repository.findAll((Specification<Venda>) (root, cq, cb) -> {
	            Predicate p = cb.conjunction();
	            
		    if (dto.getIdArtesao() != null) {
		    	p = cb.and(p, cb.greaterThanOrEqualTo(root.get("produtoEstoque").get("produto").get("artesao").get("id"), cb.literal(dto.getIdArtesao())));
		    }
		   /* if (dto.getIdLojista() != null) {
		    	p = cb.and(p, cb.greaterThanOrEqualTo(root.get("lojista").get("id"), cb.literal(dto.getIdLojista())));
		    }*/
	            
		    return p;
	        }, PageRequest.of(page, size, Sort.by(Direction.fromString(order), orderBy)));

		return pagina;
	}
	
	@Transactional
    public void excluir(Long id) {
		
		if(id != null && id > 0) {
			repository.deleteById(id);
		}
    }
	
	@Transactional
    public Venda inserir(VendaDTO novaVenda) {

		ProdutoEstoque estoque = produtoEstoqueRepository.findById(novaVenda.getIdProdutoEstoque()).get();
		
		Venda venda = Venda.builder().dataVenda(novaVenda.getDataVenda())
				.produtoEstoque(estoque).quantidade(novaVenda.getQuantidade())
		.build();

		return this.repository.save(venda);
    }
	
	@Transactional
    public Venda atualizar(VendaDTO vendaDTO) {
	
		ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findById(vendaDTO.getIdProdutoEstoque()).get();
		
		Venda venda = repository.findById(vendaDTO.getId()).get();
		
		venda.setQuantidade(vendaDTO.getQuantidade());
		venda.setDataVenda(vendaDTO.getDataVenda());
		venda.setProdutoEstoque(produtoEstoque);
	
		return this.repository.save(venda);
    }
	
}
