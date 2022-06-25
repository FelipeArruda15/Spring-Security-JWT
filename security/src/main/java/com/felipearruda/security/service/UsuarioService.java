package com.felipearruda.security.service;

import com.felipearruda.security.domain.Role;
import com.felipearruda.security.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario salvarUsuario(Usuario usuario);
    Role salvarRole(Role role);
    void addRoleParaUsuario(String username, String perfil);
    Usuario buscarUsuario(String usuario);
    List<Usuario> listarUsuarios();
}
