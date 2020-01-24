package Storage.repository;

import Storage.entity.Post;
import Storage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE " +
            "UPPER(u.username) LIKE CONCAT('%',UPPER(:username),'%') AND " +
            "u.post =  :post")
    List<User> findUsersByUsername(String username, Post post);

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.username) LIKE CONCAT('%',UPPER(:username),'%')")
    List<User> findUsersByUsername(String username);
}
