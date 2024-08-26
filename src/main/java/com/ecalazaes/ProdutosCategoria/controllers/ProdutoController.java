package com.ecalazaes.ProdutosCategoria.controllers;


import com.ecalazaes.ProdutosCategoria.entities.Produto;
import com.ecalazaes.ProdutosCategoria.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @GetMapping
    public ResponseEntity<List<Produto>> findAllProdutos(){
      List<Produto> produtos = produtoService.findAllProdutos();
      return produtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findProdutoById(@PathVariable Long id) {
      Produto produto = produtoService.findProdutoById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
       Produto novoProduto =  produtoService.saveProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
}
