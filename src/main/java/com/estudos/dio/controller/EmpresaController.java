package com.estudos.dio.controller;

import com.estudos.dio.model.Empresa;
import com.estudos.dio.model.JornadaTrabalho;
import com.estudos.dio.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa){
        return empresaService.saveEmpresa(empresa);
    }

    @GetMapping
    public List<Empresa> getEmpresaList(){
        return empresaService.findAll();
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<Empresa> getEmpresaByID(@PathVariable("idEmpresa") Long idEmpresa)
            throws Exception {
        return  ResponseEntity.ok(empresaService.getById(idEmpresa).orElseThrow(() ->
                new NoSuchElementException("Not found!")));
    }

    @PutMapping
    public Empresa updateEmpresa(@RequestBody Empresa empresa){
        return empresaService.updateEmpresa(empresa);
    }

    @DeleteMapping("/{idEmpresa}")
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
