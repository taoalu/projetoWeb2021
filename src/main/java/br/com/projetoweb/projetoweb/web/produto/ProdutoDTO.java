package br.com.projetoweb.projetoweb.web.produto;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.projetoweb.projetoweb.model.Artesao;
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
    private String descricao;
    private Double preco;
    private String imgUrl;
    private List<Artesao> artesao;
}
