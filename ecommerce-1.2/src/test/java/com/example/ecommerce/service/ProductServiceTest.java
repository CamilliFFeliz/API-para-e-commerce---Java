
package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ProductService(repo);
    }

    @Test
    public void list_returnsPage() {
        when(repo.findAll(PageRequest.of(0,10))).thenReturn(new PageImpl<>(List.of(new Product())));
        var page = service.list(0,10);
        assertEquals(1, page.getTotalElements());
    }

    @Test
    public void create_and_find() {
        Product p = new Product();
        p.setName("X");
        when(repo.save(any())).thenReturn(p);
        var created = service.create(p);
        assertEquals("X", created.getName());
    }
}
