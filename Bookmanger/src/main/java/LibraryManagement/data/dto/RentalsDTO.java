package LibraryManagement.data.dto;

import LibraryManagement.data.entity.BooksEntity;
import LibraryManagement.data.entity.MemberEntity;
import LibraryManagement.data.entity.RentalsEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalsDTO {
    private Integer rentalID;
    private MemberEntity memberEntity;
    private BooksEntity booksEntity;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Boolean rentalsStatus;

    public RentalsEntity ToEntity() {
        return new RentalsEntity(
            rentalID,
            memberEntity,
            booksEntity,
            rentalDate,
            returnDate,
            rentalsStatus
        );
    }
}