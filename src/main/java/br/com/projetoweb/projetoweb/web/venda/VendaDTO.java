package br.com.projetoweb.projetoweb.web.venda;

import java.time.LocalDateTime;

import br.com.projetoweb.projetoweb.web.produto.ProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

	 public Long id;
	 public Long idProdutoEstoque;
	 public Long idArtesao;
	 public Long idLojista;
	 public Integer quantidade;
	 public LocalDateTime dataVenda;
}
