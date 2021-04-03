package br.com.projetoweb.projetoweb.web.lojista;

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

import br.com.projetoweb.projetoweb.model.Lojista;
import br.com.projetoweb.projetoweb.service.LojistaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/lojista")
@CrossOrigin(origins = "*")
public class LojistaController {

	@Autowired
	private LojistaService service;

	@ApiOperation("Listar todos Lojista")
	@GetMapping
	public List<Lojista> listar(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String email, @RequestParam(required = false) String marca) {

		LojistaDTO lojista = LojistaDTO.builder().nome(nome).email(email).build();

		return this.service.listar(lojista);
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
