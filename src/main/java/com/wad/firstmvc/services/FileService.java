package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.File;
import com.wad.firstmvc.domain.Product;

import java.util.List;

public interface FileService {
    void saveAll(Iterable<File> files);
    List<File> findAll();
    void save(File file);
    //List<File> findByRootId(Long rootId);
    List<File> findByParentId(Long parentId);
    List<File> findByType(String type);
    List<File> findByName(String name);
    File findById(Long id);
    public List<Long> findPath(List<Long> acc, Long id);
    void delete(File file);
}
