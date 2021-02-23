package br.com.projetoweb.projetoweb.web.artesao;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.projetoweb.projetoweb.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtesaoDTO {
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	private String senha;
	private String marca;
	private List<Produto> produtos;
	
}
