package com.treizer.spring_boot_rest.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treizer.spring_boot_rest.persistence.entity.MakerEntity;
import com.treizer.spring_boot_rest.persistence.repository.IMakerRepository;
import com.treizer.spring_boot_rest.presentation.dto.MakerDto;
import com.treizer.spring_boot_rest.presentation.dto.MakerInsertDto;
import com.treizer.spring_boot_rest.presentation.dto.MakerUpdateDto;
import com.treizer.spring_boot_rest.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MakerService implements ICommonService<MakerDto, MakerInsertDto, MakerUpdateDto> {

    @Autowired
    private IMakerRepository makerRepository;

    private static final ModelMapper MAPPER = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public List<MakerDto> findAll() {
        var makers = this.makerRepository.findAll();

        return StreamSupport
                .stream(makers.spliterator(), false)
                .map(maker -> MAPPER.map(maker, MakerDto.class))
                // .map(maker -> {
                // return MakerDto.builder()
                // .id(maker.getId())
                // .name(maker.getName())
                // .products(maker.getProducts())
                // .build();
                // })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MakerDto findById(Long id) {
        var makerEntity = this.makerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el fabricante con id: " + id));

        return MAPPER.map(makerEntity, MakerDto.class);
        // return MakerDto.builder()
        // .id(makerEntity.getId())
        // .name(makerEntity.getName())
        // .products(makerEntity.getProducts())
        // .build();
    }

    @Override
    @Transactional
    public MakerDto save(MakerInsertDto insertDto) {
        try {
            var makerEntity = MAPPER.map(insertDto, MakerEntity.class);
            makerEntity = this.makerRepository.save(makerEntity);

            return MAPPER.map(makerEntity, MakerDto.class);

        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    "Error al guardar un fabricante: " + insertDto + " -> e: " + e.toString());
        }
    }

    @Override
    @Transactional
    public MakerDto update(MakerUpdateDto updateDto, Long id) {
        var makerEntity = id != null
                ? this.makerRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró al fabricante con ID: " + id))
                : this.makerRepository.findById(updateDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontró al fabricante con ID: " + updateDto.getId()));

        if (!updateDto.getName().isBlank()) {
            makerEntity.setName(updateDto.getName());
        }
        if (!updateDto.getProducts().isEmpty()) {
            makerEntity.setProducts(updateDto.getProducts());
        }

        this.makerRepository.save(makerEntity);
        return MAPPER.map(makerEntity, MakerDto.class);
    }

    @Override
    @Transactional
    public MakerDto deleteById(Long id) {
        var makerEntity = this.makerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al fabricante con ID: " + id));

        var makerDto = MAPPER.map(makerEntity, MakerDto.class);
        this.makerRepository.deleteById(id);

        return makerDto;
    }

}
