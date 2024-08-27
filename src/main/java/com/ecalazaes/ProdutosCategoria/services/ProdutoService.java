package com.ecalazaes.ProdutosCategoria.services;

import com.ecalazaes.ProdutosCategoria.entities.Produto;
import com.ecalazaes.ProdutosCategoria.repositories.CategoriaRepository;
import com.ecalazaes.ProdutosCategoria.repositories.ProdutoRepository;
import com.ecalazaes.ProdutosCategoria.services.exception.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Produto> findAllProdutos() {
       return produtoRepository.findAll();
    }

    public Produto findProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado com ID: " + id));
    }

    public Produto saveProduto(Produto produto) {
        if (produto.getCategoria().getId() == null) {
            categoriaRepository.save(produto.getCategoria());
        }
        return produtoRepository.save(produto);
    }

    public void deleteProdutoById(Long id) {
        if(produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException("Produto não encontrado com o ID: " + id);
        }
    }

    public Produto updateProduto(Long id, Produto produto) {
        return produtoRepository.findById(id)
                .map(produtoEncontrado -> {
                    produtoEncontrado.setNome(produto.getNome());
                    produtoEncontrado.setDescricao(produto.getDescricao());
                    produtoEncontrado.setPreco(produto.getPreco());
                    return produtoRepository.save(produtoEncontrado);
                        })
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado com o ID: " + id));
        }
    }