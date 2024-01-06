package LibraryManagement.data.repository;

import LibraryManagement.data.entity.RentalsEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("Rentals")
public interface RentalsRepository extends JpaRepository<RentalsEntity, Integer> {

}