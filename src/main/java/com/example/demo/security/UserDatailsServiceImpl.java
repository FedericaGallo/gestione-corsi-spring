package com.example.demo.security;

import com.example.demo.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {

    private UtenteRepository repository;

    public UserDatailsServiceImpl(UtenteRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        return repository.findByEmail(userEmail).
                orElseThrow(()-> new UsernameNotFoundException("not found"));
    }
}
