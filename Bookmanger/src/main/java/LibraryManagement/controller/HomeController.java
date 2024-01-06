package LibraryManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        String title = "도서 관리";
        String path = "home";
        String name = "home";

        LOGGER.info("[home] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[home] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[home] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // * --- Side Menu Page -----------------------------------------

    // ?사용자 정보 관리 페이지
    @GetMapping("/user/management")
    public String userManagement(Model model) {
        String title = "내 정보 관리";
        String path = "User/User-management";
        String name = "User-management";

        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        LOGGER.info("[User-management] - User authority : "+authority);
        model.addAttribute("authority", authority);

        LOGGER.info("[user-management] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[user-management] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[user-management] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // ?도서 조회 페이지
    @GetMapping("/books/page")
    public String booksPage(Model model) {
        String title = "도서 조회";
        String path = "Books/Books-page";
        String name = "Books";

        LOGGER.info("[home-Books] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[home-Books] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[home-Books] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // ?대여기록 조회 페이지
    @GetMapping("/user/rental-history")
    public String user_rentalHistory(Model model) {
        String title = "대여기록 조회";
        String path = "User/user-rental-history";
        String name = "user-rental-history";

        LOGGER.info("[home-rental-history] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[home-rental-history] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[home-rental-history] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // ?도서추천 페이지
    @GetMapping("/recom/page")
    public String recomPage(Model model) {
        String title = "추천 도서";
        String path = "Recom/Recom-page";
        String name = "Recom";

        LOGGER.info("[home-Recom] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[home-Recom] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[home-Recom] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // ?민원 페이지
    @GetMapping("/complain")
    public String complain(Model model) {
        String title = "민원 안내";
        String path = "complain";
        String name = "complain";

        LOGGER.info("[complain] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[complain] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[complain] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }
    
    // !관리자 권한 페이지
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin/page")
    public String adminPage(Model model) {
        String title = "관리자 페이지";
        String path = "Admin/Admin-page";
        String name = "Admin";

        LOGGER.info("[home-Admin] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[home-Admin] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[home-Admin] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }
}