package LibraryManagement.data.dto;

import LibraryManagement.data.entity.AuthorsEntity;
import LibraryManagement.data.entity.BooksEntity;
import LibraryManagement.data.entity.PublishersEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BooksDTO {
    private Integer BooklID;
    private String Title;
    private String AuthorName;
    private String PublisherName;
    private String ISBN;
    private Boolean Available;

    public BooksEntity ToEntity() {
        return new BooksEntity(
            BooklID,
            Title,
            new AuthorsEntity(AuthorName),
            new PublishersEntity(PublisherName),
            ISBN,
            Available
        );
    }
}
