package LibraryManagement.controller;

import LibraryManagement.data.dto.MemberDTO;
import LibraryManagement.data.entity.MemberEntity;
import LibraryManagement.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserController {
    @Autowired @Qualifier("Member")
    private MemberService memberService;

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/Agree") // ? 사용자 정보제공동의 화면으로 이동
    public String Agree(Model model) {
        LOGGER.info("[User-Agree] - /user/create -> Agree");
        return "User/Agree";
    }

    @GetMapping("/user/Agree/isCheck") // ? 정보제공동의 후 사용자 등록화면 이동
    public String ischek(@RequestParam String isCheck, Model model) {
        LOGGER.info("[User-Agree-isCheck] - isCheck : "+isCheck);

        // ? 사용자가 정보제공 동의 하지 않을 경우 리다이렉트
        if(Objects.equals(isCheck, "On")) {
            return "redirect:/User/Agree";
        }
        else { // ? 사용자가 정보제공 동의
            String title = "사용자 등록";
            String path = "User/User-create";
            String name = "User-create";

            LOGGER.info("[User-Agree-isCheck]  - title: "+title);
            model.addAttribute("title", title);
            LOGGER.info("[User-Agree-isCheck]  - template: "+path);
            model.addAttribute("path", path);
            LOGGER.info("[User-Agree-isCheck]  - name: "+name);
            model.addAttribute("name", name);
            return "layout/layout";
        }
    }

    //! 로그인한 사용자 권한확인 페이지(*함부로 사용 금지 테스트 이후 삭제할 것!)
    @GetMapping("/user/check")
    public String userCheck(Model model) {
        String title = "사용자 확인";
        String path = "User/User-check";
        String name = "User-check";

        // ?인증된 User세션에서 권한 가져오기
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        authority = authority.substring(1, authority.length() - 1);
        LOGGER.info("[User-management] - User authority : " + authority);
        model.addAttribute("authority", authority);

        LOGGER.info("[User-check]  - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[User-check]  - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[User-check]  - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    // * --- Login / Logout -------------------------------------


    @GetMapping("/user/login") // ? 사용자 인증 페이지
    public String userLogin(Model model) {
        String title = "사용자 인증";
        String path = "User/User-login";
        String name = "User-login";

        LOGGER.info("[user-certify] - title: " + title);
        model.addAttribute("title", title);
        LOGGER.info("[user-certify] - template: " + path);
        model.addAttribute("path", path);
        LOGGER.info("[user-certify] - name: " + name);
        model.addAttribute("name", name);
        return "layout/layout";
    }

    @GetMapping("/login/success") // ?Login 성공 시 동작
    public String LoginSuccess() {

        // ? 현재 인증된 User Session 가져오기
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

        // ? UserDetails 가져오기
        Object userSession = userAuthentication.getPrincipal();
        UserDetails loginUser = (UserDetails) userSession;
        // ? UserId, Name 가져오기
        String userId = loginUser.getUsername();
        String userName = memberService.getUserId(userId).getName();
        LOGGER.info("[Login Success] - success Login!");
        LOGGER.info("[Login Success] - Login Session" + "User Id: " + userId + ", User Name: " + userName);

        // ? 권한 가져오기
        String authority = userAuthentication.toString();
        authority = authority.substring(1, authority.length() - 1);
        LOGGER.info("[Login Success] - User authority : "+authority + "\n}");

        return "User/Login/LoginMsg";
    }

    // ? Login 실패 시 동작
    @GetMapping("/login/fail")
    public String LoginFail() {
        LOGGER.info("[Login Fail] - fail Login!");
        return "User/Login/LoginFailMsg";
    }

    // ? Logout 성공 시 동작
    @GetMapping("/logout/success")
    public String LogoutSuccess() {
        LOGGER.info("[Logout Success] - success logout!");
        return "User/Login/LogoutMsg";
    }


    // * --- POST (Create Data) ----------------------------------------------------------

    // ? 사용자 정보 등록 제출 유효성검사
    @PostMapping("/user/create/submit")
    public String CreateSubmit(@ModelAttribute MemberDTO memberDTO, @RequestParam String password, Model model) {
        LOGGER.info("[User-Create-Submit] - MemberDTO : "+memberDTO);
        LOGGER.info("[User-Create-Submit] - password : "+password);

        // ? 관리자 비밀번호 불일치
        if (memberDTO.EroChk(password)) { return "User/Login/AdminPassErrMsg"; }

        LOGGER.info("[User-Create-Submit] - form : " + memberDTO);

        // ? 중복 ID Check
        MemberEntity userIdCheck = memberService.getUserId(memberDTO.getUserId());
        LOGGER.info("[User-Create-Submit] - userIdCheck : " + userIdCheck);
        if (userIdCheck!=null) { return "User/Login/DuplicateIdMsg"; }

        memberService.setMember(memberDTO); // * 사용자 정보 DB 등록
        LOGGER.info("[User-Create-Submit] - Userdata succeed save!: " + memberDTO);

        // ? 로그인 페이지로 이동
        model.addAttribute("title", "사용자 인증");
        model.addAttribute("path", "User/User-login");
        model.addAttribute("name", "User-login");

        return "layout/layout";
    }

    // ? 사용자 정보 등록 페이지 리다이렉트
    @GetMapping("/user/create")
    public String Create(Model model) {
        // 페이지 리다이렉트 설정
        model.addAttribute("title", "사용자 등록");
        model.addAttribute("path", "User/User-create");
        model.addAttribute("name", "User-create");
        return "layout/layout"; // 리다이렉션
    }

    // * --- PUT (Update Data) ----------------------------------------------------------

    // * --- Delete (Delete Data) -------------------------------------------------------
}