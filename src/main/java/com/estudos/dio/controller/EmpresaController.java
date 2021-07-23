package com.estudos.dio.controller;

import com.estudos.dio.model.Empresa;
import com.estudos.dio.model.JornadaTrabalho;
import com.estudos.dio.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
@Api(value = "API REST Empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    @ApiOperation(value = "Cria uma nova empresa")
    public Empresa createEmpresa(@RequestBody Empresa empresa){
        return empresaService.saveEmpresa(empresa);
    }

    @GetMapping
    @ApiOperation(value = "Obtem uma lista de empresas")
    public List<Empresa> getEmpresaList(){
        return empresaService.findAll();
    }

    @GetMapping("/{idEmpresa}")
    @ApiOperation(value = "Obtem uma empresa por id")
    public ResponseEntity<Empresa> getEmpresaByID(@PathVariable("idEmpresa") Long idEmpresa)
            throws Exception {
        return  ResponseEntity.ok(empresaService.getById(idEmpresa).orElseThrow(() ->
                new NoSuchElementException("Not found!")));
    }

    @PutMapping
    @ApiOperation(value = "Atualiza uma empresa")
    public Empresa updateEmpresa(@RequestBody Empresa empresa){
        return empresaService.updateEmpresa(empresa);
    }

    @DeleteMapping("/{idEmpresa}")
    @ApiOperation(value = "Exclui uma empresa")
    public Optional deleteByID(@PathVariable("idEmpresa") Long idEmpresa) throws Exception {
        Optional<Empresa> empresaDeletada = null;
       try {
           empresaDeletada = empresaService.getById(idEmpresa);
           empresaService.deleteEmpresa(idEmpresa);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
        return empresaDeletada;
    }

}
