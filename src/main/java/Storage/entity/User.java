package Storage.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "usr")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Please enter your name")
    @Length(max = 255, message = "Name too long")
    private String name;

    @Column(name = "username")
    @NotBlank(message = "Please enter username")
    @Length(max = 255, message = "Password too long")
    private String username;

    @Column(name = "email")
    @NotBlank(message = "Please enter email")
    @Length(max = 255, message = "Email too long")
    @Email(message = "Is not correct")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Please enter password")
    @Length(max = 255, message = "Password too long")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role")
    @JoinColumn(name = "user_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return  username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }
}
