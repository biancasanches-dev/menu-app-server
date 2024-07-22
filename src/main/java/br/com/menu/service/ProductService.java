package br.com.menu.service;

import br.com.menu.domain.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Product findProductById(Long id);
    List<Product> listProducts();
}
