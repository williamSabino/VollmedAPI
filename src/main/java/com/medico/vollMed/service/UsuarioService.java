package com.medico.vollMed.service;

import com.medico.vollMed.domains.usuario.RegisterUsuarioDTO;
import com.medico.vollMed.domains.usuario.Usuario;
import com.medico.vollMed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public ResponseEntity registerUsuario(RegisterUsuarioDTO registerUsuarioDTO) {
        if(this.repository.findByLogin(registerUsuarioDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        } else {
            var passwordEncrypt = new BCryptPasswordEncoder().encode(registerUsuarioDTO.senha());
            Usuario usuario = new Usuario(registerUsuarioDTO.login(), passwordEncrypt, registerUsuarioDTO.role());
            repository.save(usuario);
            return ResponseEntity.ok().build();
        }
    }
}
