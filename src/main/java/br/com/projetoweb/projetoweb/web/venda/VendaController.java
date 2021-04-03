package br.com.projetoweb.projetoweb.web.venda;

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

import br.com.projetoweb.projetoweb.model.Venda;
import br.com.projetoweb.projetoweb.service.VendaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/venda")
@CrossOrigin(origins = "*")
public class VendaController {

	@Autowired
    private VendaService vendaService;
	
	@ApiOperation("Listar todas vendas")
    @GetMapping
    public List<Venda> listar(
    		@RequestParam(required = false) Long idArtesao,
    		@RequestParam(required = false) Long idLojista) 
    {
	
		VendaDTO venda = VendaDTO.builder()
				.idArtesao(idArtesao).idLojista(idLojista)
				.build();
		
	
		return this.vendaService.listar(venda);
    }
	
	@ApiOperation("Rota responsável por salvar uma Venda.")
    @PostMapping
    public ResponseEntity<Venda> inserir(@RequestBody @Valid VendaDTO venda) {

		return ResponseEntity.ok(this.vendaService.inserir(venda));
    }
	
	@ApiOperation("Rota responsável por atualizar uma venda")
    @PutMapping
    public ResponseEntity<Venda> atualizar(@RequestBody @Valid VendaDTO venda) {
	
		return ResponseEntity.ok(this.vendaService.atualizar(venda));
    }
	
	@ApiOperation("Recebe o ID da venda e a exclui do banco.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Venda> exluir(@PathVariable(required = true) Long id) {
		this.vendaService.excluir(id);
		return ResponseEntity.noContent().build();
    }
}
