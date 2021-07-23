package com.estudos.dio.controller;

import com.estudos.dio.model.JornadaTrabalho;
import com.estudos.dio.service.JornadaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/jornada")
@Api(value = "API REST Jornada de Trabalho")
@CrossOrigin(origins = "*")
public class JornadaTrabalhoController {
    @Autowired
    JornadaService jornadaService;

    @PostMapping
    @ApiOperation(value = "Cria uma nova jornada de trabalho")
    public JornadaTrabalho createJornada(@RequestBody JornadaTrabalho jornadaTrabalho){
        return jornadaService.saveJornada(jornadaTrabalho);
    }

    @GetMapping
    @ApiOperation(value = "Obtem uma lista de jornadas de trabalho")
    public List<JornadaTrabalho> getJornadaList(){
        return jornadaService.findAll();

    }

    @GetMapping("/{idJornada}")
    @ApiOperation(value = "Consulta uma jornada de trabalho por id")
    public ResponseEntity<JornadaTrabalho>  getJornadaByID(@PathVariable("idJornada") Long idJornada) throws Exception {
        return  ResponseEntity.ok(jornadaService.getById(idJornada).orElseThrow(() -> new NoSuchElementException("Not found!")));

    }

    @PutMapping
    @ApiOperation(value = "Atualiza uma jornada de trabalho")
    public JornadaTrabalho updateJornada(@RequestBody JornadaTrabalho jornadaTrabalho){
        return jornadaService.updateJornada(jornadaTrabalho);
    }

    @DeleteMapping("/{idJornada}")
    @ApiOperation(value = "Exclui uma jornada de trabalho")
    public Optional deleteByID(@PathVariable("idJornada") Long idJornada) throws Exception {
        Optional<JornadaTrabalho> jornadaDeletada = null;
       try {
           jornadaDeletada = jornadaService.getById(idJornada);
           jornadaService.deleteJornada(idJornada);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
        return jornadaDeletada;
    }



}
