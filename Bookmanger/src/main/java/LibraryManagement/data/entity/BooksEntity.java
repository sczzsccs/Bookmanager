package LibraryManagement.data.entity;

import LibraryManagement.data.dto.BooksDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BooksEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;

    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private AuthorsEntity authorsEntity; // 책 저자

    @ManyToOne(cascade = CascadeType.ALL)
    private PublishersEntity publishersEntity; // 책 출판사

    private String ISBN;

    @Column(nullable = false)
    private Boolean available = true; // 대여가능여부(가능/불가)

    BooksDTO ToDto() {
        return new BooksDTO(
            bookID,
            title,
            authorsEntity.getAuthorsName(),
            publishersEntity.getPublishersName(),
            ISBN,
            available
        );
    }

    @Override
    public String toString() {
        String Buff;
        Buff = "BookID : "+bookID+", ";
        Buff += "Title : "+title+"\n    ";
        Buff += "Author : "+authorsEntity.getAuthorsName()+", ";
        Buff += "Publisher : "+publishersEntity.getPublishersName()+"\n    ";
        Buff += "ISBN : "+ISBN+", ";
        Buff += "available : "+available;

        return Buff;
    }
}