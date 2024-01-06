package LibraryManagement.config;

import LibraryManagement.data.Login.LogoutHandler;
import LibraryManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    
    public SecurityConfig(MemberService memberService) { // UserDetailsService 주입
        this.userDetailsService = (UserDetailsService) memberService;
    }

    @Bean // PasswordEncoder 설정 : null
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null; // 사용하는 password 문자 설정
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // UserDetailsService 구현체 설정
        ;
    }

    @Bean // 정적리소스 경로 필터 무시 설정
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
            PathRequest
                .toStaticResources()
                .atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 권한 설정
        http.authorizeHttpRequests(
            (authorizeHttpRequests) -> authorizeHttpRequests
                    // 해당 경로는 모든 권한을 허용한다.
                    .requestMatchers(new AntPathRequestMatcher( "/static/**")).permitAll()

                    .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()

                    .requestMatchers(new AntPathRequestMatcher("/user/Agree/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/user/create/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/user/certify/submit")).permitAll()

                    .requestMatchers(new AntPathRequestMatcher("/recom/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/books/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/exception/**")).permitAll()

                    .requestMatchers(new AntPathRequestMatcher("/logout/success")).permitAll()

                    // 해당 경로는 어드민 권한이 있어야한다.
                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")

                    // 해당 경로는 유저 이상의 권한이 있어야 한다.
                    .requestMatchers(new AntPathRequestMatcher("/user/management")).hasAnyRole("USER", "ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/users/**")).hasAnyRole("USER", "ADMIN")

                    // 나머지는 인증된 사용자만 허용한다.
                    .anyRequest()
                    .authenticated()
        );
        
        // 로그인 설정
        http.formLogin()
                .loginPage("/user/login").permitAll() // 로그인 페이지 설정(권한이 없는 경우 접근하는 페이지Url)
                .loginProcessingUrl("/user/login/submit").permitAll() // 로그인이 수행될 uri 매핑 (post 요청이 기본)
                .defaultSuccessUrl("/login/success") // 로그인이 성공 시 uri
                .failureUrl("/login/fail").permitAll() // 로그인이 실패 시 uri
        ;

        // 로그아웃 설정 (get, post)
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(new LogoutHandler()) // 쿠키 삭제요청 Handler
                .invalidateHttpSession(true) // 로그아웃 시 HTTP 세션 무효화
                .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
        );

        // 세션관련 설정
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 사용자 인증 후 기존 세션이 없는 경우 세션 생성
            .maximumSessions(1)
            .and()
            .sessionFixation().migrateSession() // 세션 고정
        ;

        return http.build();
    }
}