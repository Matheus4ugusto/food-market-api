package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Media;
import com.academiadodesenvolvedor.market.models.Product;
import com.academiadodesenvolvedor.market.repositories.ProductRepository;
import com.academiadodesenvolvedor.market.services.contracts.ProductServiceContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceContract {

    public ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        this.repository.delete(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return this.repository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Produto não encontrado."));
    }

    @Override
    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Product setMedia(Product product, Media media) {
        if(product.getMedias() != null){
            product.getMedias().add(media);
            return product;
        }

        List<Media> medias = new ArrayList<>();
        medias.add(media);
        product.setMedias(medias);
        return product;
    }

    @Override
    public Product setMedia(Product product, List<Media> media) {
        if(product.getMedias() != null){
            media.stream().forEach((mediaMap -> {
                product.getMedias().add(mediaMap);
            }));
            return product;
        }

        product.setMedias(media);
        return product;
    }
}
