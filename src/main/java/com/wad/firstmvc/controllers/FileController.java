package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.File;
import com.wad.firstmvc.domain.Magarie;
import com.wad.firstmvc.domain.Product;
import com.wad.firstmvc.services.FileService;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping
    public String viewProfilePage(Model model){
        model.addAttribute("repositories", fileService.findByType("repository"));
        return "profilePage";
    }

    @GetMapping("/repositories")
    public String viewRepositoryPage(Model model){
        model.addAttribute("foundFile",fileService.findAll());
        return "repositoryPage";
    }


    @GetMapping("/find")
    public String findProduct(@RequestParam("parentId") String parentId, Model model) {
        Long actualParentId;
        if (parentId.charAt(0) == '\'')
            actualParentId = Long.parseLong(parentId.substring(1, parentId.length() - 1));
        else
            actualParentId = Long.parseLong(parentId);

        List<File> files = fileService.findByParentId(actualParentId);
        model.addAttribute("parentId", actualParentId);

        File rootFile = fileService.findById(actualParentId);


        List<Long> rootIds = fileService.findPath(new ArrayList<Long>(), actualParentId);

        if (!rootIds.isEmpty()) {
            List<String> rootNames = new ArrayList<>();
            for (Long rootId : rootIds)
                rootNames.add(fileService.findById(rootId).name);

            List<Magarie> magarii = new ArrayList<>();

            for (int i = 0; i < rootIds.size(); ++i) {
                Magarie magarie = new Magarie(rootIds.get(i), rootNames.get(i));
                magarii.add(magarie);
            }

            Collections.reverse(magarii);
            model.addAttribute("magarii", magarii);
        }

        if (rootFile.content != null) {
            List<String> contentLines = Arrays.stream(rootFile.content.split("(?<=\n)")).toList();
            model.addAttribute("contentLines", contentLines);
            return "repositoryFilePage";
        }

        if (files != null) {
            model.addAttribute("foundFile", files);
            return "repositoryPage";
        } else {
            model.addAttribute("errorMessage", "Files not found");
            return "error";
        }
    }

    @GetMapping("/newRepository")
    public String showAddRepositoryForm(Model model) {
        model.addAttribute("file",new File());
        return "addNewRepository";
    }

    @PostMapping("/newRepository")
    public String addRepository(File file){
        file.setParentId(0L);

        if(file.getId()==null)
            file.setId(new Random().nextLong());
        file.finishedOn = LocalDate.now();
        file.setType("repository");
        file.setCategory("category");
        fileService.save(file);
        return "redirect:/files";
    }

    @GetMapping("/newFile")
    public String showAddFileForm(@RequestParam("parentId") String parentId, Model model) {
        model.addAttribute("parentId",parentId);
        model.addAttribute("name",fileService.findById(Long.parseLong(parentId)).name);
        model.addAttribute("file",new File());
        return "addNewFile";
    }

    @PostMapping("/newFile")
    public String addFile(File file){
        if(file.getId()==null)
            file.setId(new Random().nextLong());
        file.finishedOn = LocalDate.now();
        fileService.save(file);
        return "redirect:/files/find?parentId='" + file.parentId + "'";
    }

    @GetMapping("/remove")
    public String deleteFile(@RequestParam("parentId") String parentId, @RequestParam("id") String id, Model model){
        File file = fileService.findById(Long.parseLong(id));
        fileService.delete(file);
        return "redirect:/files/find?parentId='" + file.parentId + "'";
    }

    @GetMapping("/findByName")
    public String showFindPatientForm(Model model) {
        return "findByName";
    }

    @PostMapping("/findByName")
    public String findFileByName(@RequestParam("name") String name, Model model){
        List<File> files = fileService.findByName(name);
        List<List<Magarie>> magariiLaPatrat = new ArrayList<>();

        for (File file : files) {
            List<Long> rootIds = fileService.findPath(new ArrayList<Long>(), file.id);
            List<String> rootNames = new ArrayList<>();
            for (Long rootId : rootIds)
                rootNames.add(fileService.findById(rootId).name);

            List<Magarie> magarii = new ArrayList<>();

            for (int i = 0; i < rootIds.size(); ++i) {
                Magarie magarie = new Magarie(rootIds.get(i), rootNames.get(i));
                magarii.add(magarie);
            }

            Collections.reverse(magarii);
            magariiLaPatrat.add(magarii);
        }
        model.addAttribute("listaDeMagarii", magariiLaPatrat);
        return "foundFiles";
    }
}
