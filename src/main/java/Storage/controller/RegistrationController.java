package Storage.controller;

import Storage.entity.User;
import Storage.repository.PostRepository;
import Storage.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/registration")
    public String getRegistration(Model model){
        model.addAttribute("posts", postRepository.findAll());
        return "registration";
    }

    @GetMapping("/wait")
    public String waiting(){
        return "wait";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("passwordToo") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model) throws MessagingException, IOException, TemplateException {
        boolean isConfirmEmpty = passwordConfirm.isEmpty();
        model.addAttribute("posts", postRepository.findAll());
        if(passwordConfirm.isEmpty()){
            model.addAttribute("passwordTooError", "Password confirmation is null");
        }

        boolean isDifferentPassword = user.getPassword().equals(passwordConfirm);
        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordTooError", "Password are different");
        }

        if(!isDifferentPassword || isConfirmEmpty || bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user",null);
            return "registration";
        }

        if(!userService.addUser(user)) {
            model.addAttribute("usernameError", "user exists!");
            return "registration";
        }
        return "redirect:/wait";
    }
}
