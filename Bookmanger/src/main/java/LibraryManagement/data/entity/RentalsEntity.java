package LibraryManagement.data.entity;

import LibraryManagement.data.dto.RentalsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data @Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rentals")
public class RentalsEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalID;

    @ManyToOne(cascade = CascadeType.ALL)
    private MemberEntity memberEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private BooksEntity bookEntity;

    private LocalDateTime rentalDate; // 대여 시작일
    private LocalDateTime returnDate; // 대여 반납일자

    @Column(nullable = false)
    private Boolean rentalsStatus; // 대여가능 여부(가능/불가)

    public boolean setStatus(boolean status) {
        if (bookEntity != null) {
            bookEntity.setAvailable(rentalsStatus = status);
            return true;
        } else {
            return false;
        }
    }

    RentalsDTO ToDto() {
        return new RentalsDTO(
            rentalID,
            memberEntity,
            bookEntity,
            rentalDate,
            returnDate,
            rentalsStatus
        );
    }

    @Override
    public String toString() {
        String Buff;
        Buff = "RentalID : "+rentalID+"\n ";
        Buff += "Member : "+memberEntity+"\n\n  ";
        Buff += "Book : "+bookEntity+"\n\n   ";
        if (rentalDate!=null) {
            Buff += "RentalDate : " + rentalDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            ) + ", ";
        }
        if (returnDate!=null) {
            Buff += "ReturnDate : " + returnDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            ) + ", ";
        }
        Buff += "RentalStatus : "+rentalsStatus;
        return Buff;
    }
}