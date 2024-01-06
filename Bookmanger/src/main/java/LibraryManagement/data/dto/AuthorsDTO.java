package LibraryManagement.data.dto;

import LibraryManagement.data.entity.AuthorsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorsDTO {
    private String authorsName;

    public AuthorsEntity ToEntity() {
        return new AuthorsEntity(authorsName);
    }
}