package com.ecalazaes.ProdutosCategoria.services;

import com.ecalazaes.ProdutosCategoria.entities.Categoria;
import com.ecalazaes.ProdutosCategoria.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAllCategorias(){
        return categoriaRepository.findAll();
    }
    
}
