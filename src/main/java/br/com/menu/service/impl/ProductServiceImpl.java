package br.com.menu.service.impl;

import br.com.menu.domain.model.Product;
import br.com.menu.domain.repository.ProductRepository;
import br.com.menu.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (productRepository.findAll().contains(product)) {
            throw new IllegalArgumentException("Product already exists");
        }
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!productRepository.findAll().contains(product)) {
            throw new IllegalArgumentException("Product does not exist");
        }
        productRepository.save(product);
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product does not exist"));
    }

    @Override
    public void deleteProduct(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
