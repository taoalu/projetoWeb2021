package br.com.projetoweb.projetoweb.web.estoque;

import java.time.LocalDateTime;
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

import br.com.projetoweb.projetoweb.model.ProdutoEstoque;
import br.com.projetoweb.projetoweb.service.EstoqueService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/estoque")
@CrossOrigin(origins = "*")
public class EstoqueController {

	@Autowired
    private EstoqueService produtoEstoqueService;
	
	@ApiOperation("Listar todos estoques")
    @GetMapping
    public List<ProdutoEstoque> listar(@RequestParam(required = false) LocalDateTime dataReposicao,
    		@RequestParam(required = false) Long idProduto,
    		@RequestParam(required = false) Double preco,
    		@RequestParam(required = false) Integer quantidade) 
    {
	
		ProdutoEstoqueDTO estoque = ProdutoEstoqueDTO.builder()
				.dataReposicao(dataReposicao)
				.idProduto(idProduto)
				.quantidade(quantidade)
				.build();
		
	
		return this.produtoEstoqueService.listar(estoque);
    }
	
	@ApiOperation("Rota responsável por salvar um estoque.")
    @PostMapping
    public ResponseEntity<ProdutoEstoque> inserir(@RequestBody @Valid ProdutoEstoqueDTO est) {

		return ResponseEntity.ok(this.produtoEstoqueService.inserir(est));
    }
	
	@ApiOperation("Rota responsável por atualizar um estoque")
    @PutMapping
    public ResponseEntity<ProdutoEstoque> atualizar(@RequestBody @Valid ProdutoEstoqueDTO estoque) {
	
		return ResponseEntity.ok(this.produtoEstoqueService.atualizar(estoque));
    }
	
	@ApiOperation("Recebe o ID da produto e o exclui do banco.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoEstoqueDTO> exluir(@PathVariable(required = true) Long id) {
		this.produtoEstoqueService.excluir(id);
		return ResponseEntity.noContent().build();
    }
	
}
