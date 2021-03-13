package br.com.projetoweb.projetoweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoweb.projetoweb.repository.ProdutoRepository;
import br.com.projetoweb.projetoweb.model.Produto;
import br.com.projetoweb.projetoweb.web.produto.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Optional<Produto> obter(long id) {
        return this.repository.findById(id);
    }

    public List<Produto> listarProdutos() {
        return this.repository.findAll();
    }

    public Produto inserir(ProdutoDTO p) {
        Produto produto = Produto.builder().descricao(p.getDescricao()).preco(p.getPreco()).imgUrl(p.getImgUrl())
                .build();
        return this.repository.save(produto);
    }

}
