package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String fone;
    private String login;
    private String email;
    private LocalDate dataNascimento;
    private StatusUsuario status; // Ativo ou Inativo
    private String password;
    private int role;

    public Usuario(String login,String password,int role, String nome, String fone, String email, LocalDate dataNascimento){
        this.login = login;
        this.password = password;
        this.role = role;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        this.dataNascimento = dataNascimento;

    }

    @ManyToMany
    @JoinTable(
            name = "usuario_categoria",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categoriasPreferidas=new HashSet<>();
    public void addCategoriaPreferida(Categoria categoria) {
        categoriasPreferidas.add(categoria);
        categoria.getUsuarios().add(this);
    }

    public void removeCategoriaPreferida(Categoria categoria) {
        categoriasPreferidas.remove(categoria);
        categoria.getUsuarios().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public Set<Categoria> getCategoriasPreferidas() {
        return categoriasPreferidas;
    }

    public void setCategoriasPreferidas(Set<Categoria> categoriasPreferidas) {
        this.categoriasPreferidas = categoriasPreferidas;
    }

    public UserRole getRole() {
        return UserRole.valueOf(role);
    }

    //Define o papel (role) do usuário a partir de um enum UserRole.
    public void setRole(UserRole role) {
        if(role != null){
            this.role = role.getCode();
        }
    }
    // Retorna as autorizações do usuário com base no papel
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.getRole()==UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    // Conta não expira
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    // Conta não está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    // Credenciais não expiram
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    // Conta está habilitada
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
