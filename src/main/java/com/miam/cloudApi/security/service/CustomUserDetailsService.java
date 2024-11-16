package com.miam.cloudApi.security.service;

import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //busca al usuario por su username o email
        var account = accountRepository.findAccountByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró al usuario con el username o email: " + email));

        //crea y retorna un objeto que representa al usuario autenticado
        return User.withUsername(account.getEmail())
                .password(account.getPassword())
                .build();
    }
}

