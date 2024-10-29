package com.treizer.spring_boot_rest.presentation.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.treizer.spring_boot_rest.presentation.dto.ProductDto;
import com.treizer.spring_boot_rest.presentation.dto.ProductInsertDto;
import com.treizer.spring_boot_rest.presentation.dto.ProductUpdateDto;
import com.treizer.spring_boot_rest.service.interfaces.IProductService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService<ProductDto, ProductInsertDto, ProductUpdateDto> productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(this.productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var productDto = this.productService.findById(id);

        return productDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductInsertDto productInsertDto) {
        // if (productInsertDto.getName().isBlank()
        // || productInsertDto.getPrice() == null
        // || productInsertDto.getMaker() == null) {
        // return ResponseEntity.badRequest().build();
        // }

        var productDto = this.productService.save(productInsertDto);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productDto.getId())
                        .toUri())
                .body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductUpdateDto productUpdateDto,
            @PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var productDto = this.productService.update(productUpdateDto, id);

        return productDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var productDto = this.productService.deleteById(id);

        return productDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productDto);
    }

    @GetMapping("/min/{minPrice}/max/{maxPrice}")
    public ResponseEntity<List<ProductDto>> findByPriceInRange(@PathVariable BigDecimal minPrice,
            @PathVariable BigDecimal maxPrice) {
        return ResponseEntity.ok(this.productService.findByPriceInRange(minPrice, maxPrice));
    }

}
