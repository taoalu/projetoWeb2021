package br.com.projetoweb.projetoweb.web.artesao;

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

import br.com.projetoweb.projetoweb.model.Artesao;
import br.com.projetoweb.projetoweb.service.ArtesaoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/artesao")
@CrossOrigin(origins = "*")
public class ArtesaoController {

	@Autowired
	private ArtesaoService service;

	@ApiOperation("Listar todos artesao")
	@GetMapping
	public List<Artesao> listar(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String email, 
			@RequestParam(required = false) String marca) {

		ArtesaoDTO artesao = ArtesaoDTO.builder().nome(nome).email(email).marca(marca).build();

		return this.service.listar(artesao);
	}

	@ApiOperation("Rota responsável por salvar um Artesao.")
	@PostMapping
	public ResponseEntity<Artesao> inserir(@RequestBody @Valid ArtesaoDTO artesao) {

		return ResponseEntity.ok(this.service.inserir(artesao));
	}

	@ApiOperation("Rota responsável por atualizar um Artesao")
	@PutMapping
	public ResponseEntity<Artesao> atualizar(@RequestBody @Valid ArtesaoDTO artesao) {

		return ResponseEntity.ok(this.service.atualizar(artesao));
	}

	@ApiOperation("Recebe o ID da artesao e a exclui do banco.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Artesao> exluir(@PathVariable(required = true) Long id) {
		this.service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
