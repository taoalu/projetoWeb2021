package br.com.projetoweb.projetoweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LOJISTA")
@Builder
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lojista implements Serializable{

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
    protected String nome;
    
    @Size (min = 3,max = 20)    
    @Pattern (regexp = "((?=.*\\p{Digit}) (?=.*\\p{Lower}) (?=.*\\p{Upper}).{3,30})")
    @Column(name = "SENHA")
    protected String senha;
    
    @Email
    @Size (max = 255)
    @Column(name = "EMAIL")
    protected String email;
	
	@OneToMany(targetEntity = Loja.class, orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
	public List<Loja> lojas;
}
