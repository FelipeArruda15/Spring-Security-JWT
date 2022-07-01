package com.felipearruda.security.service.impl;

import com.felipearruda.security.domain.Role;
import com.felipearruda.security.domain.Usuario;
import com.felipearruda.security.repositories.RoleRepository;
import com.felipearruda.security.repositories.UsuarioRepository;
import com.felipearruda.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null){
            log.info("Usuário não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }else{
            log.info("Usuário: {} encontrado com sucesso", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getRoles().forEach((role -> {
            authorities.add(new SimpleGrantedAuthority(role.getPerfil()));
        }));

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getSenha(), authorities);
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        log.info("Salvando o usuário {}", usuario.getNome());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Role salvarRole(Role role) {
        log.info("Salvando a role {}", role.getPerfil());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleParaUsuario(String username, String perfil) {
        log.info("Adicionando a role {} ao usuário {}", perfil, username);
        Usuario usuario = usuarioRepository.findByUsername(username);
        Role role = roleRepository.findByPerfil(perfil);
        usuario.getRoles().add(role);
    }

    @Override
    public Usuario buscarUsuario(String usuario) {
        log.info("Buscando o usuário {}", usuario);
        return usuarioRepository.findByUsername(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        log.info("Buscando todos os usuários");
        return usuarioRepository.findAll();
    }

}
