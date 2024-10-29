package com.treizer.spring_boot_rest.presentation.dto;

import java.math.BigDecimal;

import com.treizer.spring_boot_rest.persistence.entity.MakerEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    String name;
    BigDecimal price;
    MakerEntity maker;

}
