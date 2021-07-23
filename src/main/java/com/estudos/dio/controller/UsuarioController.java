package com.estudos.dio.controller;

import com.estudos.dio.model.JornadaTrabalho;
import com.estudos.dio.model.Usuario;
import com.estudos.dio.service.JornadaService;
import com.estudos.dio.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@Api(value = "API REST de Usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @ApiOperation(value = "Cria um novo usuario")
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping
    @ApiOperation(value = "Obtem uma lista de usuarios")
    public List<Usuario> getUsuarioList(){
            return usuarioService.findAll();
    }

    @GetMapping("/{idUsuario}")
    @ApiOperation(value = "Consulta uma usuario por id")
    public ResponseEntity<Usuario>  getUsuarioById(@PathVariable("idUsuario") Long idUsuario) throws Exception {
        return  ResponseEntity.ok(usuarioService.getById(idUsuario).orElseThrow(() ->
                new NoSuchElementException("Not found!")));

    }

    @PutMapping
    @ApiOperation(value = "Atualiza um usuario")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    @ApiOperation(value = "Exclui um usuario")
    public Optional deleteById(@PathVariable("idUsuario") Long idUsuario) throws Exception {
        Optional<Usuario> usuarioDeletado = null;
       try {
           usuarioDeletado = usuarioService.getById(idUsuario);
           usuarioService.deleteUsuario(idUsuario);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
        return usuarioDeletado;
    }
}
