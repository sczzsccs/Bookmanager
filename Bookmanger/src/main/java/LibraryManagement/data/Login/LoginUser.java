package LibraryManagement.data.Login;

import LibraryManagement.data.entity.MemberEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class LoginUser implements UserDetails { // Spring Security가 세션에서 관리하는 사용자정보 객체
    private final Logger LOGGER = LoggerFactory.getLogger(LoginUser.class);
    private final MemberEntity memberEntity; // DB에서 조회한 사용자정보(Entity)
    public LoginUser(MemberEntity member) { // 생성자를 통해 전달 받음
        memberEntity = member;
        LOGGER.info("[UserDetails] - Entity : "+member.toString());
    }

    @Override // 사용자 권한을 반환하는 메서드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> Levels = new ArrayList<>();
        Levels.add(new SimpleGrantedAuthority("ROLE_" + memberEntity.getLevel().toString()));
        LOGGER.info("[UserDetails] - getAuthorities :"+Levels.toString());
        return Levels;
    }

    @Override // 사용 X
    public String getPassword() {
        return null; // password 대신 userId 사용
    }

    @Override // 사용자 Id 반환 (UserName Pk)
    public String getUsername() {
        return memberEntity.getMemberId();
    }

    @Override // 계정이 만료 되었는지 (true: 만료X)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정이 잠겼는지 (true: 잠기지 않음)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호가 만료되었는지 (true: 만료X)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정이 활성화(사용가능)인지 (true: 활성화
    public boolean isEnabled() {
        return true;
    }
}