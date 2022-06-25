package com.felipearruda.security.service.impl;

import com.felipearruda.security.domain.Role;
import com.felipearruda.security.domain.Usuario;
import com.felipearruda.security.repositories.RoleRepository;
import com.felipearruda.security.repositories.UsuarioRepository;
import com.felipearruda.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Role salvarRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleParaUsuario(String username, String perfil) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        Role role = roleRepository.findByPerfil(perfil);
        usuario.getRoles().add(role);
    }

    @Override
    public Usuario buscarUsuario(String usuario) {
        return usuarioRepository.findByUsername(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
