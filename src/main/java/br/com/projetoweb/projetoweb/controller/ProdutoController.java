package br.com.projetoweb.projetoweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoweb.projetoweb.model.Produto;
import br.com.projetoweb.projetoweb.model.Lojista;
import br.com.projetoweb.projetoweb.service.ArtesaoService;
import br.com.projetoweb.projetoweb.service.LojistaService;
import br.com.projetoweb.projetoweb.service.ProdutoService;
import br.com.projetoweb.projetoweb.web.produto.ProdutoDTO;
import io.swagger.annotations.ApiOperation;

import br.com.projetoweb.projetoweb.web.lojista.LojistaDTO;

@RestController
@RequestMapping("api/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	@ApiOperation("Listar todos Produtos")
	@GetMapping
	public Page<Produto> listar(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String email, @RequestParam(required = false) String marca,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "15") Integer size,
			@RequestParam(required = false, defaultValue = "id") String orderBy,
			@RequestParam(required = false, defaultValue = "desc") String order) {

		ProdutoDTO produto = ProdutoDTO.builder().descricao();

		return this.service.listarProdutos(produto, page - 1, size, orderBy, order);
	}

	@ApiOperation("Rota responsável por salvar um Lojista.")
	@PostMapping
	public ResponseEntity<Lojista> inserir(@RequestBody @Valid LojistaDTO lojista) {

		return ResponseEntity.ok(this.service.inserir(lojista));
	}

	@ApiOperation("Rota responsável por atualizar um Lojista")
	@PutMapping
	public ResponseEntity<Lojista> atualizar(@RequestBody @Valid LojistaDTO lojista) {

		return ResponseEntity.ok(this.service.atualizar(lojista));
	}

	@ApiOperation("Recebe o ID da Lojista e a exclui do banco.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Lojista> exluir(@PathVariable(required = true) Long id) {
		this.service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
