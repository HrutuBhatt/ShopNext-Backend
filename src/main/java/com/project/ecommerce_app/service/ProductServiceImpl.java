package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Product;
import com.project.ecommerce_app.entity.User;
import com.project.ecommerce_app.repository.ProductRepository;
import com.project.ecommerce_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Product> updateProduct(Integer id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setStock(product.getStock());
            updatedProduct.setCategory(product.getCategory());
            return Optional.of(productRepository.save(updatedProduct));
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Product> getProductByUserId(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return productRepository.findByUser(user);

    }

}
