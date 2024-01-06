package LibraryManagement.data.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class LogoutHandler implements LogoutSuccessHandler {
    private Logger LOGGER = LoggerFactory.getLogger(LogoutHandler.class);

    @Override // 모든 쿠키 삭제
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // 모든 쿠키 가져오기
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 쿠키의 유효기간을 0으로 설정하여 삭제
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        LOGGER.info("[Logout Handler] - Success Cookie Deleted!");

        // 로그아웃 성공 시 리다이렉션
        response.sendRedirect("/logout/success");
    }
}