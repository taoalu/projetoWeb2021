package br.com.projetoweb.projetoweb.web.lojista;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.projetoweb.projetoweb.model.Loja;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LojistaDTO {

	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	private String senha;
	private List<Loja> lojas;
}
