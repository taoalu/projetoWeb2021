package br.com.projetoweb.projetoweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Endereco implements Serializable {

	@Column(name = "NUMERO")
    protected Integer numero;
	@Column(name = "RUA")
	protected String rua;
	@Column(name = "BAIRRO")
	protected String bairro;
	@Column(name = "CIDADE")
	protected String cidade;
	@Column(name = "ESTADO")
	protected String uf;
	@Column(name = "CEP")
	protected String cep;
	@Column(name = "COMPLEMENTO")
	protected String complemento;
}
