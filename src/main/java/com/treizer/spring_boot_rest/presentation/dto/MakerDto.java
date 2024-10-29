package com.treizer.spring_boot_rest.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import com.treizer.spring_boot_rest.persistence.entity.ProductEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// This works, but if you want declare all
// attributes with the @Builder, it works.
// If you want use ModelMapper, it doesn't work.
// @Value
// @Builder
// @NoArgsConstructor(force = true)
// @AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MakerDto {

    Long id;
    String name;
    // List<ProductEntity> products;
    @Builder.Default
    List<ProductEntity> products = new ArrayList<>();

}
