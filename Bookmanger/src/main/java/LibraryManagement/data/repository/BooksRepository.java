package LibraryManagement.data.repository;

import LibraryManagement.data.entity.BooksEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Qualifier("Books")
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {
   BooksEntity findByBookID(int booksId);
   List<Object> findByTitle(String title);
   @Query("select Book from BooksEntity Book where Book.available = true")
   List<Object> findByAvailable();

   @Query("SELECT book FROM BooksEntity book WHERE book.authorsEntity.authorsName = :authorsName")
   List<BooksEntity> findByAuthorsName(@Param("authorsName") String authorsName);
   @Query("SELECT book FROM BooksEntity book WHERE book.publishersEntity.publishersName = :publishersName")
   List<BooksEntity> findByPublishersName(@Param("publishersName") String publishersName);

   @Query("select Book from BooksEntity Book where Book.available = true and Book.title like %:Title%")
   List<Object> findByAvailableTitle(@Param("Title") String title);

   @Query("select Book.title, " +
         "Book.authorsEntity.authorsName, " +
         "Book.publishersEntity.publishersName, " +
         "Book.available " +
            "from BooksEntity Book " +
            "where Book.available = true and Book.title like %:Title%")
   List<Object> findByAvailableTitle2(@Param("Title") String title);
}