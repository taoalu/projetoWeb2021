package br.com.projetoweb.projetoweb.web.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    protected String descricao;
    protected Double preco;
    protected Long idArtesao;
}
