package LibraryManagement.data.entity;

import LibraryManagement.data.dto.AuthorsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class AuthorsEntity {
    @Id
    private String authorsName;

    AuthorsDTO ToDto() {
        return new AuthorsDTO(authorsName);
    }
}