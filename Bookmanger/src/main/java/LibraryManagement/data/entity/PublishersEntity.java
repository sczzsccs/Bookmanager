package LibraryManagement.data.entity;

import LibraryManagement.data.dto.PublishersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishers")
public class PublishersEntity {
    @Id
    private String publishersName;

    PublishersDTO toDto() {
        return new PublishersDTO(publishersName);
    }
}