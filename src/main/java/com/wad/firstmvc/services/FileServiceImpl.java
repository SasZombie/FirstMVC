package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.File;
import com.wad.firstmvc.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
//to see some changes later
@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveAll(Iterable<File> files) {
        fileRepository.saveAll(files);
    }

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public void save(File file) {
        fileRepository.save(file);
    }
    @Override
    public void delete(File file) {
        fileRepository.delete(file);
    }

    /*@Override
    public List<File> findByRootId(Long rootId) {
        List<File> files = fileRepository.findAll();
        List<File> goodFiles = new LinkedList<>();
        for(File file : files)
            if(Objects.equals(file.rootId, rootId))
                goodFiles.add(file);

    return goodFiles;
    }*/

    @Override
    public List<File> findByParentId(Long parentId) {
        return fileRepository.findByParentId(parentId);
    }
    @Override
    public List<File> findByType(String type) {
        return fileRepository.findByType(type);
    }
    @Override
    public File findById(Long id){
        Optional<File> result = fileRepository.findById(id);
        return result.orElse(null);
    }
    @Override
    public List<File> findByName(String name){
        return fileRepository.findByName(name);
    }

    public List<Long> findPath(List<Long> acc, Long id) {
        File crt = findById(id);
        if(Objects.equals(crt.type, "repository")) {
            acc.add(id);
            return acc;
        }

        if(crt.parentId == 0L)
            return acc;
        else {
            acc.add(id);
            return findPath(acc, crt.parentId);
        }
    }
}
