package com.treizer.spring_boot_rest.presentation.controller;

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

import com.treizer.spring_boot_rest.presentation.dto.MakerDto;
import com.treizer.spring_boot_rest.presentation.dto.MakerInsertDto;
import com.treizer.spring_boot_rest.presentation.dto.MakerUpdateDto;
import com.treizer.spring_boot_rest.service.interfaces.ICommonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

    @Autowired
    private ICommonService<MakerDto, MakerInsertDto, MakerUpdateDto> makerService;

    @GetMapping
    public ResponseEntity<List<MakerDto>> findAll() {
        return ResponseEntity.ok(this.makerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MakerDto> findById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var makerDto = this.makerService.findById(id);

        return (makerDto == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(makerDto);
    }

    @PostMapping
    public ResponseEntity<MakerDto> save(@Valid @RequestBody MakerInsertDto makerInsertDto) {
        // if (makerInsertDto.getName().isBlank()) {
        // return ResponseEntity.badRequest().build();
        // }

        var makerDto = this.makerService.save(makerInsertDto);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(makerDto.getId())
                        .toUri())
                .body(makerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MakerDto> update(@RequestBody MakerUpdateDto makerUpdateDto, @PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var makerDto = this.makerService.update(makerUpdateDto, id);

        return makerDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(makerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MakerDto> deleteById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
            // return ResponseEntity.badRequest().body(null);
        }

        var makerDto = this.makerService.deleteById(id);

        return makerDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(makerDto);
    }

}
