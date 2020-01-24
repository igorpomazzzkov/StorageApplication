package Storage.service;

import Storage.entity.Post;
import Storage.entity.Role;
import Storage.entity.User;
import Storage.repository.PostRepository;
import Storage.repository.UserRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    public static final String subject = "STORAGE APPLICATION Activate user";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MailSender mailSender;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    private void sendToAdmin(User user) throws MessagingException, IOException, TemplateException {
        if (!user.getEmail().isBlank()) {
            Map<String, String> model = new HashMap();
            model.put("name", user.getName());
            model.put("username", user.getUsername());
            model.put("email", user.getEmail());
            model.put("post", user.getPost().getName());
            mailSender.send("edeltitnu@gmail.com", subject,  model, "message.ftl");
        }
    }

    public boolean addUser(User user) throws MessagingException, IOException, TemplateException {
        User userFromDB = userRepository.findAllByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sendToAdmin(user);
        return true;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void activateUser(User user, Map<String, String> form){
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key: form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        Post post = postRepository.findAllByName(form.get("post"));
        user.setPost(post);
        user.setName(form.get("name"));
        if(form.get("save") == null){
            user.setActive(true);
            Map<String, String> model = new HashMap();
            model.put("name", user.getName());
            model.put("username", user.getUsername());
            model.put("email", user.getEmail());
            model.put("post", user.getPost().getName());
            try {
                mailSender.send(user.getEmail(), subject, model, "messageToUser.ftl");
            } catch (IOException | MessagingException | TemplateException e) {
                e.printStackTrace();
            }
        }
        user.getRoles().add(Role.USER);
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> find(String username, Post post){
        return userRepository.findUsersByUsername(username, post);
    }

    public List<User> findByUsername(String username){
        return userRepository.findUsersByUsername(username);
    }
}
