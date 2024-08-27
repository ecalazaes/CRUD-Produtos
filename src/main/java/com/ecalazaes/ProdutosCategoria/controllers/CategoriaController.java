package com.ecalazaes.ProdutosCategoria.controllers;

import com.ecalazaes.ProdutosCategoria.entities.Categoria;
import com.ecalazaes.ProdutosCategoria.entities.Produto;
import com.ecalazaes.ProdutosCategoria.services.CategoriaService;
import com.ecalazaes.ProdutosCategoria.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAllCategorias() {
        List<Categoria> categorias = categoriaService.findAllCategorias();
        return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.findCategoriaById(id);
            return ResponseEntity.ok(categoria);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.saveCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoriaById(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            categoriaService.updateCategoria(id, categoria);
            return ResponseEntity.ok(categoria);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
