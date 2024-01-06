package LibraryManagement.data.dto;

import LibraryManagement.data.entity.MemberEntity;
import LibraryManagement.data.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String userId;
    private String Name;
    private String PhoneNum;
    private String Email;
    private UserRole Level;

    public MemberDTO(String memberID, String name, String email, String phoneNum) {
        this.userId=memberID;
        this.Name=name;
        this.Email=email;
        this.PhoneNum=phoneNum;
    }

    public boolean EroChk(String password) {
        if (Level.equals(UserRole.ADMIN)
            && !(password.equals("ADMIN"))) {
            return true;
        }
        return false;
    }

    public MemberEntity ToEntity() {
        return new MemberEntity(
            userId,
            Name,
            PhoneNum,
            Email,
            Level
        );
    }
}