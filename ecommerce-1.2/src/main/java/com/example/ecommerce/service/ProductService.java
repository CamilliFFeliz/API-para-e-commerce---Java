
package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Page<Product> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public Product update(Long id, Product p) {
        if (!repo.existsById(id)) throw new NotFoundException("Produto não encontrado");
        p.setId(id);
        return repo.save(p);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Produto não encontrado");
        repo.deleteById(id);
    }
}
