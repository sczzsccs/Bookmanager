package LibraryManagement.controller;

import LibraryManagement.data.repository.AuthorsRepository;
import LibraryManagement.data.repository.BooksRepository;
import LibraryManagement.data.repository.PublishersRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class testAddUserController
{
    // Dependencies Injection
    @Autowired @Qualifier("Books")
    private BooksRepository booksRepository;
    @Autowired @Qualifier("Authors")
    public AuthorsRepository authorsRepository;
    @Autowired @Qualifier("Publishers")
    private PublishersRepository publishersRepository;

    // debugging
    private Logger LOGGER = LoggerFactory.getLogger(testAddUserController.class);



    // MVC Get method View road

    @GetMapping(value = "/adduser")
    public String Adduser() {
        LOGGER.info("[Adduser] - 호출 됨 - AddUser");
        return "AddUser";
    }

    // MVC Get method parameter
    @GetMapping(value = "/userURL")
    public String testUrl(@ModelAttribute String userUrl, Model model) {
        // 요청 body 데이터에 접근
        LOGGER.info("[testUrl] - userUrl: " + userUrl);
        // 요청 파라미터 'userUrl'의 값을 가져옵니다.
        model.addAttribute("userUrl", userUrl);

        // 'userUrl' 속성을 가진 뷰를 반환합니다.
        return "BookRecom/DoRecom";
    }


    // Rest Controller

    @RequestMapping("/aa") @ResponseBody
    public String aa() {
        LOGGER.info("[aa] - 호출 됨 - AddUser");
        return "AddUser";
    }

    // test data class
    @Data
    class Form {
        private String st_name;
        private String st_id;
        private String department;
    }

    // test View Road
    @GetMapping(value = "/test-get")
    public String TestVirew(Model model) {
        return "homeTest";
    }


    // Post method
    @PostMapping("/test-post")
    public ResponseEntity<Void> TestPost(
        @ModelAttribute String title,
        @ModelAttribute String content
    ) {
        LOGGER.info("[test-post] - title : " + title);
        LOGGER.info("[test-post] - content : " + content);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test-post2") @ResponseBody
    public void TestPost2(
        @RequestParam String title
    ) {
        LOGGER.info("[test-post] - title : " + title);
    }

    @PostMapping("/test-post/form")
    public ResponseEntity<Void> postFormTest(@ModelAttribute Form form) {
        LOGGER.info("[test-post] - form : "+form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test-post/form2") @ResponseBody
    public void postFormTest2(@RequestBody Form form) {
        LOGGER.info("[test-post] - form : "+form);
    }


    // Put methode

    @PutMapping("/test-put")
    public ResponseEntity<Void> TestPut(
        @ModelAttribute String title,
        @ModelAttribute String content
    ) {
        LOGGER.info("[test-post] - title : " + title);
        LOGGER.info("[test-post] - content : " + content);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/test-put/form")
    public ResponseEntity<Void> puttFormTest(@ModelAttribute Form form) {
        LOGGER.info("[test-post] - form : "+form);
        return ResponseEntity.ok().build();
    }

    // Delete methode
    @DeleteMapping("/test-delete")
    public void deleteTest() {
    }
}