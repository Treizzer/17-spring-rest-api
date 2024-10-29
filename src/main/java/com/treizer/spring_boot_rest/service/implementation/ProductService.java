package com.treizer.spring_boot_rest.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treizer.spring_boot_rest.persistence.entity.ProductEntity;
import com.treizer.spring_boot_rest.persistence.repository.IProductRepository;
import com.treizer.spring_boot_rest.presentation.dto.ProductDto;
import com.treizer.spring_boot_rest.presentation.dto.ProductInsertDto;
import com.treizer.spring_boot_rest.presentation.dto.ProductUpdateDto;
import com.treizer.spring_boot_rest.service.interfaces.IProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService implements IProductService<ProductDto, ProductInsertDto, ProductUpdateDto> {

    @Autowired
    private IProductRepository productRepository;

    private static final ModelMapper MAPPER = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        var products = this.productRepository.findAll();

        return StreamSupport
                .stream(products.spliterator(), false)
                .map(product -> MAPPER.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        var productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + id));

        return MAPPER.map(productEntity, ProductDto.class);
    }

    @Override
    @Transactional
    public ProductDto save(ProductInsertDto insertDto) {
        try {
            var productEntity = MAPPER.map(insertDto, ProductEntity.class);
            productEntity = this.productRepository.save(productEntity);

            return MAPPER.map(productEntity, ProductDto.class);

        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    "No fue posible guardar el producto: " + insertDto + " -> e: " + e.toString());
        }
    }

    @Override
    @Transactional
    public ProductDto update(ProductUpdateDto updateDto, Long id) {
        var productEntity = id != null
                ? this.productRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + id))
                : this.productRepository.findById(updateDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontró el producto con ID: " + updateDto.getId()));

        if (!updateDto.getName().isBlank()) {
            productEntity.setName(updateDto.getName());
        }
        if (updateDto.getPrice() != null) {
            productEntity.setPrice(updateDto.getPrice());
        }
        if (updateDto.getMaker() != null) {
            productEntity.setMaker(updateDto.getMaker());
        }

        this.productRepository.save(productEntity);
        return MAPPER.map(productEntity, ProductDto.class);
    }

    @Override
    @Transactional
    public ProductDto deleteById(Long id) {
        var productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + id));

        var productDto = MAPPER.map(productEntity, ProductDto.class);
        this.productRepository.deleteById(id);

        return productDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        var products = this.productRepository.findByPriceInRange(minPrice, maxPrice);

        return products.stream()
                .map(product -> MAPPER.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

}
