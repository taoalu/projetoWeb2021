package br.com.projetoweb.projetoweb.web.estoque;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.projetoweb.projetoweb.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEstoqueDTO {

	private Long id;
    protected Long idProduto;
	protected Integer quantidade;
	protected LocalDateTime dataReposicao;
	
}
