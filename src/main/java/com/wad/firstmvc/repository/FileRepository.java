package com.wad.firstmvc.repository;

import com.wad.firstmvc.domain.File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends CrudRepository<File,Long> {
    List<File> findAll();
    List<File> findByParentId(Long parentId);
    Optional<File> findById(Long id);
    List<File> findByType(String type);
    List<File> findByName(String name);
}
