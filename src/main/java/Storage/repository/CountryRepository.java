package Storage.repository;

import Storage.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByOrderByName();
    Country findAllById(long id);
}
