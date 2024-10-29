package com.treizer.spring_boot_rest.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import com.treizer.spring_boot_rest.persistence.entity.ProductEntity;

import lombok.Value;

@Value
public class MakerUpdateDto {

    Long id;
    String name;
    List<ProductEntity> products = new ArrayList<>();

}
