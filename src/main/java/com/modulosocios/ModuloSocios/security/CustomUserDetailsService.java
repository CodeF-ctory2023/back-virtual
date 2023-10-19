package com.modulosocios.ModuloSocios.security;

import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.repository.AdministradorRepository;
import com.modulosocios.ModuloSocios.repository.SociosRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private SociosRepository sociosRepository;

    private AdministradorRepository administradorRepository;

    public CustomUserDetailsService(SociosRepository sociosRepository,
                                    AdministradorRepository administradorRepository) {
        this.sociosRepository = sociosRepository;
        this.administradorRepository = administradorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Socios socio = sociosRepository.findByCorreoElectronico(email);

        if (socio != null) {
            return new org.springframework.security.core.userdetails.User(socio.getCorreoElectronico(),
                    socio.getContrasena(),
                    List.of(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority("USER")}));
        } else {
            Administrador admin = administradorRepository.findByCorreoElectronicoAdmin(email);
            if (admin != null) {
                return new org.springframework.security.core.userdetails.User(admin.getCorreoElectronicoAdmin(),
                        admin.getContrasenaAdmin(),
                        List.of(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority("ADMIN")}));
            } else {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
        }
    }
}
