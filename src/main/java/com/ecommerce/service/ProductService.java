package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaProducerService kafkaProducer;

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        kafkaProducer.publishStatusEvent("PRODUCT", savedProduct.getId().toString(), 
                "CREATED", "Product created: " + savedProduct.getName());
        return savedProduct;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with sku: " + sku));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getActiveProducts() {
        return productRepository.findByIsActiveTrue();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByIsActiveTrueAndCategory(category);
    }

    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        if (productDetails.getName() != null) {
            product.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null) {
            product.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null) {
            product.setPrice(productDetails.getPrice());
        }
        if (productDetails.getStockQuantity() != null) {
            product.setStockQuantity(productDetails.getStockQuantity());
        }
        if (productDetails.getCategory() != null) {
            product.setCategory(productDetails.getCategory());
        }
        if (productDetails.getImageUrl() != null) {
            product.setImageUrl(productDetails.getImageUrl());
        }
        if (productDetails.getIsActive() != null) {
            product.setIsActive(productDetails.getIsActive());
        }
        Product updatedProduct = productRepository.save(product);
        kafkaProducer.publishStatusEvent("PRODUCT", updatedProduct.getId().toString(), 
                "UPDATED", "Product updated: " + updatedProduct.getName());
        return updatedProduct;
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        kafkaProducer.publishStatusEvent("PRODUCT", id.toString(), 
                "DELETED", "Product deleted with id: " + id);
    }

    public boolean reduceStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        if (product.getStockQuantity() < quantity) {
            return false;
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);
        return true;
    }

    public void increaseStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productRepository.save(product);
    }
}

