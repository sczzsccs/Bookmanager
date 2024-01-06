package LibraryManagement.data.entity;

import LibraryManagement.data.dto.MemberDTO;
import LibraryManagement.data.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class MemberEntity {

    @Id @Column(unique = true)
    private String memberId;

    private String Name;

    @Column(unique = true)
    private String PhoneNum;

    @Column(unique = true)
    private String Email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole Level; // 사용자 등급(일반사용자/관리자)

    MemberDTO ToDto() {
        return new MemberDTO(
            memberId,
            Name,
            Email,
            PhoneNum,
            Level
        );
    }

    @Override
    public String toString() {
        String Buff;
        Buff = "MemberID : "+ memberId +", ";
        Buff += "Name : "+Name+"\n    ";
        Buff += "PhoneNum : "+PhoneNum+", ";
        Buff += "Email : "+Email+", ";
        Buff += "Level : "+Level;

        return Buff;
    }
}