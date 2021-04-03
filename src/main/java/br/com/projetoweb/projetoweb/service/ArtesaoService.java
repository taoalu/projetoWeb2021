package br.com.projetoweb.projetoweb.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoweb.projetoweb.model.Artesao;
import br.com.projetoweb.projetoweb.repository.ArtesaoRepository;
import br.com.projetoweb.projetoweb.web.artesao.ArtesaoDTO;

@Service
public class ArtesaoService {

	@Autowired
	private ArtesaoRepository repository;

	public Optional<Artesao> obter(Long id) {
		return this.repository.findById(id);
	}

	public List<Artesao> listarTodos() {
		return repository.findAll();
	}

	public List<Artesao> listar(ArtesaoDTO dto) {

		List<Artesao> pagina = this.repository.findAll((Specification<Artesao>) (root, cq, cb) -> {
			Predicate p = cb.conjunction();

			if (dto.getNome() != null) {
				p = cb.and(p, cb.greaterThanOrEqualTo(root.get("nome"), cb.literal(dto.getNome())));
			}
			if (dto.getEmail() != null) {
				p = cb.and(p, cb.lessThanOrEqualTo(root.get("email"), cb.literal(dto.getEmail())));
			}
			if (dto.getMarca() != null) {
				p = cb.and(p, cb.lessThanOrEqualTo(root.get("marca"), cb.literal(dto.getMarca())));
			}

			return p;
		});

		return pagina;
	}

	@Transactional
	public Artesao inserir(ArtesaoDTO novoArtesao) {

		Artesao artesao = Artesao.builder().marca(novoArtesao.getMarca()).produtos(novoArtesao.getProdutos())
				.nome(novoArtesao.getNome()).email(novoArtesao.getEmail()).senha(novoArtesao.getSenha()).build();

		return this.repository.save(artesao);
	}

	@Transactional
	public Artesao atualizar(ArtesaoDTO artesaoDTO) {

		Artesao artesao = repository.findById(artesaoDTO.getId()).get();

		artesao.setNome(artesaoDTO.getNome());
		artesao.setEmail(artesaoDTO.getEmail());
		artesao.setMarca(artesaoDTO.getMarca());
		artesao.setSenha(artesaoDTO.getSenha());
		artesao.setProdutos(artesaoDTO.getProdutos());

		return this.repository.save(artesao);
	}

	@Transactional
	public void excluir(Long id) {

		if (id != null && id > 0) {
			repository.deleteById(id);
		}
	}
}
