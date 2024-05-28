package com.wad.firstmvc.bootstrap;

import com.wad.firstmvc.domain.File;
import com.wad.firstmvc.domain.User;
import com.wad.firstmvc.services.FileService;
import com.wad.firstmvc.services.UserService;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class DataLoader implements CommandLineRunner {
  private final FileService fileService;
  private final UserService userService;

  public DataLoader(FileService fileService, UserService userService) {
    this.fileService = fileService;
    this.userService = userService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<File> fileList = List.of(
            new File("FirstMVC", "1", 0L, LocalDate.now(), "repository", null),
            new File(".idea", "2", 1L, LocalDate.now(), "folder", null),
            new File("src", "3", 1L, LocalDate.now(), "folder", null),
            new File("target", "4", 1L, LocalDate.now(), "folder", null),
            new File(".gitignore", "5", 1L, LocalDate.now(), "file", null),
            new File(".mvnw", "6", 1L, LocalDate.now(), "file", null),
            new File("mvnw.cmd", "7", 1L, LocalDate.now(), "file", null),
            new File("pom.xml", "8", 1L, LocalDate.now(), "file", null),
            new File("main", "9", 3L, LocalDate.now(), "folder", null),
            new File("test", "10", 3L, LocalDate.now(), "folder", null),
            new File("java", "11", 9L, LocalDate.now(), "folder", null),
            new File("resources", "12", 9L, LocalDate.now(), "folder", null),
            new File("com", "13", 11L, LocalDate.now(), "folder", null),
            new File("wad", "14", 13L, LocalDate.now(), "folder", null),
            new File("firstmvc", "15", 14L, LocalDate.now(), "folder", null),
            new File("bootstrap", "16", 15L, LocalDate.now(), "folder", null),
            new File("controllers", "17", 15L, LocalDate.now(), "folder", null),
            new File("domain", "18", 15L, LocalDate.now(), "folder", null),
            new File("repositories", "19", 15L, LocalDate.now(), "folder", null),
            new File("services", "20", 15L, LocalDate.now(), "folder", null),
            new File("FirstMvcApplicatoin.java", "21", 15L, LocalDate.now(), "file", "content".strip()),
            new File("DataLoader.java", "22", 16L, LocalDate.now(), "file", null),
            new File("FileController.java", "23", 17L, LocalDate.now(), "file", null),
            new File("HomeController.java", "24", 17L, LocalDate.now(), "file", null),
            new File("File.java", "25", 18L, LocalDate.now(), "file", null),
            new File("Magarie.java", "26", 18L, LocalDate.now(), "file", null),
            new File("FileRepository.java", "27", 19L, LocalDate.now(), "file", null),
            new File("ServiceRepository.java", "28", 20L, LocalDate.now(), "file", null),
            new File("ServiceRepositoryImpl.java", "29", 20L, LocalDate.now(), "file", null)
    );
    fileService.saveAll(fileList);
    /*
    List<Product> productList = List.of(
            new Product("Car", ProductType.HARDWARE, "Nice car",134000 ),
            new Product("Computer", ProductType.HARDWARE, "Lenovo",2500 ),
            new Product("Office", ProductType.SOFTWARE, "Office",670 ),
            new Product("Windows", ProductType.SOFTWARE, "win",500 ),
            new Product("Cake", ProductType.HARDWARE, "Sweet",20 )
    );
    productService.saveAll(productList);

    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"));
    user2.getRoles().add(Role.ROLE_ADMIN);
    userService.save(user1);
    userService.save(user2);
    */
  }
}
