package LibraryManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private final String title = "관리자 페이지";


    @GetMapping("/")
    public String adminPage(Model model) {
        String path = "Admin/Admin-page";
        String name = "Admin";

        LOGGER.info("[admin-Page] - title: "+title);
        model.addAttribute("title", title);
        LOGGER.info("[admin-Page] - template: "+path);
        model.addAttribute("path", path);
        LOGGER.info("[admin-Page] - name: "+name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    /* --- GET URI (Page Load :: Return View) ----------------------------------------------------------*/

    @GetMapping("/book-create")
    public String bookCreate(Model model) {
        String path = "Admin/Admin-book-create";
        String name = "Admin-book-create";

        LOGGER.info("[admin-bookCreate] - title: "+title);
        model.addAttribute("title", title);
        LOGGER.info("[admin-bookCreate] - template: "+path);
        model.addAttribute("path", path);
        LOGGER.info("[admin-bookCreate] - name: "+name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    @GetMapping("/book-delete")
    public String bookDelete(Model model) {
        String path = "Admin/Admin-book-delete";
        String name = "Admin-book-delete";

        LOGGER.info("[admin-bookDelete] - title: "+title);
        model.addAttribute("title", title);
        LOGGER.info("[admin-bookDelete] - template: "+path);
        model.addAttribute("path", path);
        LOGGER.info("[admin-bookDelete] - name: "+name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    @GetMapping("/users")
    public String users(Model model) {
        String path = "Admin/Admin-users";
        String name = "Admin-users";

        LOGGER.info("[admin-users] - title: "+title);
        model.addAttribute("title", title);
        LOGGER.info("[admin-users] - template: "+path);
        model.addAttribute("path", path);
        LOGGER.info("[admin-users] - name: "+name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // http://localhost/admin/users-history?userId=3
    @GetMapping("/users-history")
    public String usersHistory(
        @RequestParam String userId, Model model
    ) {
        String path = "Admin/Admin-users-history";
        String name = "Admin-users-history";

        LOGGER.info("[admin-users-history] - User Id: "+userId);
        model.addAttribute("userId", userId);

        LOGGER.info("[admin-users-history] - title: "+title);
        model.addAttribute("title", title);
        LOGGER.info("[admin-users-history] - template: "+path);
        model.addAttribute("path", path);
        LOGGER.info("[admin-users-history] - name: "+name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    /* --- POST (Create Data) ----------------------------------------------------------*/

    /* --- PUT (Update Data) ----------------------------------------------------------*/

    /* --- Delete (Delete Data) ----------------------------------------------------------*/

}