package com.treizer.spring_boot_rest.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import com.treizer.spring_boot_rest.persistence.entity.ProductEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

// If you don't declare @Builder, the program return 400.
// JSON parse error: Cannot construct instance of `com.treizer.spring_boot_rest.presentation.dto.MakerInsertDto` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator)
@Value
@Builder
public class MakerInsertDto {

    @NotBlank(message = "El nombre NO puede ser nulo o vacío")
    @Size(min = 2, message = "El nombre debe tener al menos dos caracteres")
    String name;

    @Builder.Default
    List<ProductEntity> products = new ArrayList<>();

}
