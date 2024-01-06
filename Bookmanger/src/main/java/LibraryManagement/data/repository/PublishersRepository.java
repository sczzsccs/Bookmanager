package LibraryManagement.data.repository;

import LibraryManagement.data.entity.PublishersEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("Publishers")
public interface PublishersRepository extends JpaRepository<PublishersEntity, String> {
   PublishersEntity findByPublishersName(String publishersName);
   boolean existsByPublishersName(String publishersName);
}