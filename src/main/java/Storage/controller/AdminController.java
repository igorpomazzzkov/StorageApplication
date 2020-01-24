package Storage.controller;

import Storage.entity.Post;
import Storage.entity.Role;
import Storage.entity.Storage;
import Storage.entity.User;
import Storage.repository.PostRepository;
import Storage.repository.StorageRepository;
import Storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private StorageRepository storageRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/activate/{username}")
    public String activateUser(Model model, @PathVariable String username){
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", userService.loadUserByUsername(username));
        return "registration";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("activate/{username}")
    public String activateUser(@RequestParam Map<String,String> form, @PathVariable String username){
        User user = userService.loadUserByUsername(username);
        List<Storage> storages = storageRepository.findAllByOrderByName();
        userService.activateUser(user, form);
        return "redirect:/storage/id=" + storages.get(0).getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String adminPage(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("posts", postRepository.findAll());
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("NoActive/{username}")
    public String disActive(Model model, @PathVariable String username){
        User user = userService.loadUserByUsername(username);
        user.setActive(false);
        if(user.getRoles().contains(Role.ADMIN)){
            user.getRoles().clear();
            user.getRoles().add(Role.USER);
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("delete/{username}")
    public String deleteUser(@PathVariable String username){
        User user = userService.loadUserByUsername(username);
        userService.deleteUser(user);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("search")
    public String search(@RequestParam Map<String, String> form, Model model){
        model.addAttribute("posts", postRepository.findAll());
        Post post = postRepository.findAllByName(form.get("post"));
        if(post != null){
            model.addAttribute("users", userService.find(form.get("username"), post));
        }else{
            model.addAttribute("users", userService.findByUsername(form.get("username")));
        }
        return "admin";
    }
}
