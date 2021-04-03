package br.com.projetoweb.projetoweb.web.produto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.projetoweb.projetoweb.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
    private ProdutoService produtoService;
	
	@ApiOperation("Listar todos artesao")
    @GetMapping
    public List<Produto> listar(@RequestParam(required = false) String descricao,
    		@RequestParam(required = false) Long idArtesao,
    		@RequestParam(required = false) Double preco) 
    {
	
		ProdutoDTO prod = ProdutoDTO.builder()
				.descricao(descricao)
				.idArtesao(idArtesao)
				.preco(preco)
				.build();
		
	
		return this.produtoService.listar(prod);
    }
	
	@ApiOperation("Rota responsável por salvar um Produto.")
    @PostMapping
    public ResponseEntity<Produto> inserir(@RequestBody @Valid ProdutoDTO prod) {

		return ResponseEntity.ok(this.produtoService.inserir(prod));
    }
	
	@ApiOperation("Rota responsável por atualizar um Produto")
    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid ProdutoDTO produto) {
	
		return ResponseEntity.ok(this.produtoService.atualizar(produto));
    }
	
	@ApiOperation("Recebe o ID da produto e o exclui do banco.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> exluir(@PathVariable(required = true) Long id) {
		this.produtoService.excluir(id);
		return ResponseEntity.noContent().build();
    }
	
}
