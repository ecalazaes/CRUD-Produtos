package com.ecalazaes.ProdutosCategoria.services;

import com.ecalazaes.ProdutosCategoria.entities.Categoria;
import com.ecalazaes.ProdutosCategoria.repositories.CategoriaRepository;
import com.ecalazaes.ProdutosCategoria.services.exception.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAllCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria findCategoriaById(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com ID: " + id));
    }

    public Categoria saveCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoriaById(Long id){
        if(categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException("Categoria não encontrada com o ID: " + id);
        }
    }

    public Categoria updateCategoria(Long id, Categoria categoria){
        return categoriaRepository.findById(id)
                .map(categoriaEncontrada -> {
                    categoriaEncontrada.setDescricao(categoria.getDescricao());
                    return categoriaRepository.save(categoriaEncontrada);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com o ID: " + id));
    }
}
