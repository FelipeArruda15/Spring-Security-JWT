package com.felipearruda.security.api;

import com.felipearruda.security.domain.Role;
import com.felipearruda.security.domain.Usuario;
import com.felipearruda.security.service.UsuarioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> buscarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @PostMapping("/role/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Role salvarRole(@RequestBody Role role){
        return usuarioService.salvarRole(role);
    }

    @PostMapping("/role/addAoUsuario")
    public void addRoleaoUsuario(@RequestBody FormFuncaodoUsuario form){
        usuarioService.addRoleParaUsuario(form.getUsername(), form.getPerfil());
    }
}

@Data
class FormFuncaodoUsuario{
    private String username;
    private String perfil;
}
