package Storage.repository;

import Storage.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    List<Producer> findAllByOrderByName();
    Producer findAllById(long id);
}
