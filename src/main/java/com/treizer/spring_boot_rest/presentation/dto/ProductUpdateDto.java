package com.treizer.spring_boot_rest.presentation.dto;

import java.math.BigDecimal;

import com.treizer.spring_boot_rest.persistence.entity.MakerEntity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductUpdateDto {

    Long id;
    String name;
    BigDecimal price;
    MakerEntity maker;

}
