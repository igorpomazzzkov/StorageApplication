package Storage.controller;

import Storage.entity.Product;
import Storage.entity.Role;
import Storage.entity.Storage;
import Storage.entity.User;
import Storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @GetMapping("/")
    public String getStorage(@AuthenticationPrincipal User user) {
        List<Storage> storages = storageRepository.findAllByOrderByName();
        if(user.getRoles().equals(Collections.singleton(Role.ADMIN))){
            return "redirect:/admin";
        }
        return "redirect:/storage/id=" + storages.get(0).getId();
    }

    @GetMapping("/storage/id={id}")
    public String getStorage(Model model, @AuthenticationPrincipal User user, @PathVariable long id) {
        if(user.getRoles().equals(Collections.singleton(Role.ADMIN))){
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("storages", storageRepository.findAllByOrderByName());
        model.addAttribute("storageID", id);
        model.addAttribute("storage", storageRepository.findAllByIdOrderByName(id));
        model.addAttribute("articles", articleRepository.findAllByOrderByName());
        model.addAttribute("countries", countryRepository.findAllByOrderByName());
        model.addAttribute("producers", producerRepository.findAllByOrderByName());
        Storage storage = storageRepository.findAllByIdOrderByName(id);
        List<Product> products = productRepository.findAllByStorage(storage);
        model.addAttribute("products", productRepository.findAllByStorage(storage));
        return "storage";
    }


    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @PostMapping("/storageAdd")
    public String addStorage(@RequestParam Map<String, String> form){
        Storage storage = new Storage();
        if(!form.get("addStorage").isBlank()){
            storage.setName(form.get("addStorage"));
            storage.setAddress(form.get("addStorage"));
            storageRepository.save(storage);
        }
        return "redirect:/";
    }

    @GetMapping("/deleteStorage/id={id}")
    public String deleteStorage(@PathVariable long id){
        storageRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/updateStorage/{id}")
    public String updateStorage(@PathVariable long id, @RequestParam Map<String, String> form){
        Storage storage = storageRepository.findAllByIdOrderByName(id);
        storage.setName(form.get("name"));
        storage.setAddress(form.get("address"));
        storageRepository.save(storage);
        return "redirect:/";
    }

    @GetMapping("/updateProduct/{id}")
    public void updateProduct(@PathVariable long id, Model model){
        Product product = productRepository.findAllByBarCode(id);
        model.addAttribute("product", product);
        System.out.println(product.getName());
    }
}
