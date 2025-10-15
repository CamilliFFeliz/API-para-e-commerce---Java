
package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final StorageService storageService;

    public ProductController(ProductService service, StorageService storageService) { this.service = service; this.storageService = storageService; }

    @GetMapping
    public Page<Product> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.list(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Product> create(@RequestPart("product") Product p, @RequestPart(value = "image", required = false) MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            String filename = storageService.store(image);
            p.setImagePath(filename);
        }
        return ResponseEntity.ok(service.create(p));
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestPart("product") Product p, @RequestPart(value = "image", required = false) MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            String filename = storageService.store(image);
            p.setImagePath(filename);
        }
        return ResponseEntity.ok(service.update(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<?> serveImage(@PathVariable String filename) throws MalformedURLException {
        Path file = storageService.load(filename);
        Resource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
