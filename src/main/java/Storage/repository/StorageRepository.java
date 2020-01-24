package Storage.repository;

import Storage.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    Storage findAllByIdOrderByName(long id);
    List<Storage> findAllByOrderByName();
}
