package br.com.projetoweb.projetoweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class Usuario implements Serializable {

	    @Size (max = 50)
	    @Column(name = "NOME")
	    protected String nome;
	    @Size (min = 3,max = 20)
	    @Pattern (regexp = "((?=.*\\p{Digit}) (?=.*\\p{Lower}) (?=.*\\p{Upper}).{3,30})")
	    @Column(name = "SENHA")
	    protected String senha;
	    @Email
	    @Size (max = 35)
	    @Column(name = "EMAIL")
	    protected String email;
}
