package br.com.projetoweb.projetoweb.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="ESTOQUE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoEstoque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID", nullable = false)
    protected Produto produto;
	
	@Column(name="QUANTIDADE")
	protected Integer quantidade;
	
	@Column(name="QUANTIDADE")
	protected LocalDateTime dataReposicao;
}
