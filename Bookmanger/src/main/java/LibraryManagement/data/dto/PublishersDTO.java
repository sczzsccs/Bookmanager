package LibraryManagement.data.dto;

import LibraryManagement.data.entity.PublishersEntity;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishersDTO {
    private String PublishersName;

    public PublishersEntity ToEntity() {
        return new PublishersEntity(PublishersName);
    }
}