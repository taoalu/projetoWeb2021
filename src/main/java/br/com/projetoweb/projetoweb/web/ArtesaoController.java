package br.com.projetoweb.projetoweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	    public Page<Artesao> listar(@RequestParam(required = false) String nome,
	    		@RequestParam(required = false) String email,
	    		@RequestParam(required = false) String marca,
	    		@RequestParam(required = false, defaultValue = "1") Integer page,
    			@RequestParam(required = false, defaultValue = "15") Integer size,
    			@RequestParam(required = false, defaultValue = "id") String orderBy,
    			@RequestParam(required = false, defaultValue = "desc") String order) 
	    {
		
			ArtesaoDTO artesao = ArtesaoDTO.builder()
					.nome(nome)
					.email(email)
					.marca(marca)
					.build();
			
		
			return this.service.listar(artesao, page - 1, size, orderBy, order);
	    }
}
