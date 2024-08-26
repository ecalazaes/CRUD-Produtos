package com.ecalazaes.ProdutosCategoria.configs;

import com.ecalazaes.ProdutosCategoria.entities.Categoria;
import com.ecalazaes.ProdutosCategoria.entities.Produto;
import com.ecalazaes.ProdutosCategoria.repositories.CategoriaRepository;
import com.ecalazaes.ProdutosCategoria.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instancias implements CommandLineRunner {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public Instancias(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        produtoRepository.deleteAll();
        categoriaRepository.deleteAll();

        Categoria categoria1 = new Categoria(null, "Eletrônico");
        Categoria categoria2 = new Categoria(null, "Roupas");

        Produto produto1 = new Produto(null, "Smartphone Galaxy S21", "Smartphone com tela de 6.2 polegadas, 128GB de armazenamento, câmera tripla de 64MP, e processador Exynos 2100.",  4.499, categoria1);
        Produto produto2 = new Produto(null, "Notebook Dell Inspiron 15 3000", "Notebook com processador Intel Core i5, 8GB de RAM, 256GB SSD, e tela de 15.6 polegadas Full HD.",  3.299, categoria1);
        Produto produto3 = new Produto(null, "Fone de Ouvido Sony WH-1000XM4", "Fone de ouvido sem fio com cancelamento de ruído ativo, som de alta resolução e bateria de até 30 horas.",  1.799, categoria1);
        Produto produto4 = new Produto(null, "Camisa Polo Lacoste", "Camisa polo de algodão piquê com ajuste clássico e logotipo do crocodilo no peito.",  349.00, categoria2);
        Produto produto5 = new Produto(null, "Calça Jeans Levi's 511", "Calça jeans slim fit com corte moderno, confeccionada em algodão com elastano para maior conforto.",  299.00, categoria2);

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));

        categoria1.getProdutos().addAll((Arrays.asList(produto1, produto2, produto3)));
        categoria2.getProdutos().addAll((Arrays.asList(produto4, produto5)));

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));

    }
}
