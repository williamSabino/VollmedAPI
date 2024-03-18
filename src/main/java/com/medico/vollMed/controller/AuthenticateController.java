package com.medico.vollMed.controller;

import com.medico.vollMed.domains.usuario.AuthenticarUsuario;
import com.medico.vollMed.domains.usuario.RegisterUsuarioDTO;
import com.medico.vollMed.domains.usuario.Usuario;
import com.medico.vollMed.infra.security.TokenJWTDTO;
import com.medico.vollMed.infra.security.TokenJWTService;
import com.medico.vollMed.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthenticateController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenJWTService tokenJWTService;

    @PostMapping("/login")
    public ResponseEntity autenticar(@Valid @RequestBody AuthenticarUsuario authenticarUsuario){
        var token = new UsernamePasswordAuthenticationToken(authenticarUsuario.login(),authenticarUsuario.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenJWTService.TokenJWTService((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterUsuarioDTO registerUsuarioDTO){
        return usuarioService.registerUsuario(registerUsuarioDTO);
    }
}
