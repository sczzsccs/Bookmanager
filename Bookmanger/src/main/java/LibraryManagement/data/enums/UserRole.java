package LibraryManagement.data.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("USER"), /* 일반 사용자 */
    ADMIN("ADMIN"); /* 관리자 */

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}