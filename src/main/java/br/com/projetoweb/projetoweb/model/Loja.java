package br.com.projetoweb.projetoweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="LOJA")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
    @Size (max = 255)
    @Column(name = "NOME")
    private String nome;
	
    @Embedded
	private Endereco endereco;
	
    @OneToMany(targetEntity = ProdutoEstoque.class, orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
 	private List<ProdutoEstoque> produtoEstoque;
	
    @OneToMany(targetEntity = Venda.class, orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
	private List<Venda> vendas;
}
