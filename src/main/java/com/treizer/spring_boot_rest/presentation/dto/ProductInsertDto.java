package com.treizer.spring_boot_rest.presentation.dto;

import java.math.BigDecimal;

import com.treizer.spring_boot_rest.persistence.entity.MakerEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductInsertDto {

    @NotBlank(message = "El nombre NO puede ser nulo o vacío")
    @Size(min = 2, message = "El nombre debe tener al menos dos caracteres")
    String name;

    @NotNull(message = "El precio NO puede ser nulo")
    @Min(value = 0, message = "El valor minímo NO puede ser menor a cero")
    BigDecimal price;

    @NotNull(message = "El fabricante NO puede ser nulo")
    MakerEntity maker;

}
