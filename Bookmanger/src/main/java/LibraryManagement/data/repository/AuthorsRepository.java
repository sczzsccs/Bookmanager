package LibraryManagement.data.repository;

import LibraryManagement.data.entity.AuthorsEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("Authors")
public interface AuthorsRepository extends JpaRepository<AuthorsEntity, String>
{
    AuthorsEntity findByAuthorsName(String authorsName);
    boolean existsByAuthorsName(String authorsName);
}