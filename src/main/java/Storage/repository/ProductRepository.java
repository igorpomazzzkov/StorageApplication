package Storage.repository;

import Storage.entity.Product;
import Storage.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStorage(Storage storage);
    Product findAllByBarCode(long barcode);
    Product findAllById(long id);
}
