package com.challenge.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

@Entity(name = "tb_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String password;

    @Column(unique = true)
    private String cpf_cnpj;

    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private MoneyWallet moneyWallet;

    private EROLE role;
    public User(String username, String password, String cpf_cnpj, String email, MoneyWallet moneyWallet,EROLE role){
            this.username = username;
            this.password = password;
            this.cpf_cnpj = cpf_cnpj;
            this.email = email;
            this.moneyWallet = moneyWallet;
            this.role = role;
    }

    public User(){};
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty String getUsername() {
        return username;
    }

    public EROLE getRole() {
        return role;
    }

    public void setRole(EROLE role) {
        this.role = role;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == EROLE.USER) return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_SHOPKEEPER"));
    }

    public @NotEmpty @Size(min = 5, max = 50) String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 5, max = 50) String password) {
        this.password = password;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public @Email @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty String email) {
        this.email = email;
    }

    public MoneyWallet getMoneyWallet() {
        return moneyWallet;
    }

    public void setMoneyWallet(MoneyWallet moneyWallet) {
        this.moneyWallet = moneyWallet;
    }
}
