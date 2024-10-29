package com.treizer.spring_boot_rest.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.treizer.spring_boot_rest.persistence.entity.MakerEntity;

@Repository
public interface IMakerRepository extends CrudRepository<MakerEntity, Long> {

}
