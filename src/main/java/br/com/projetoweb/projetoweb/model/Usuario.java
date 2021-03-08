package br.com.projetoweb.projetoweb.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "USUARIO")
@Builder
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario implements UserDetails  {

	
	private static final long serialVersionUID = 5255727504681610495L;

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String username;

    @JsonIgnore
    @NotEmpty
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public String getPassword() {
	return this.password;
    }

    public String getUsername() {
	return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
    	return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
    	return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
    	return true;
    }
}
