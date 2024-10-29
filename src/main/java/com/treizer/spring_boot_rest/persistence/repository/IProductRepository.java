package com.treizer.spring_boot_rest.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.treizer.spring_boot_rest.persistence.entity.ProductEntity;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long> {

    // @Query("SELECT p FROM ProductEntity p WHERE p.price >= ?1 AND p.price <= ?2")
    @Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN ?1 AND ?2")
    List<ProductEntity> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
