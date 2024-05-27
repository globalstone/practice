package com.model2.mvc.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.kakao.KakaoService;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {

	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
	@Autowired
	@Qualifier("kakaoServiceImpl")
	private KakaoService kakaoService;

	public UserRestController(){
		System.out.println(this.getClass());
	}

	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{

		System.out.println("/user/json/getUser : GET");

		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
						  HttpSession session ) throws Exception{

		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser = userService.getUser(user.getUserId());

		if (dbUser != null && !user.getPassword().equals(dbUser.getPassword())) {
			// 비밀번호가 일치하지 않으면 예외를 던집니다.
			throw new Exception("비밀번호가 잘못되었습니다.");
		}

		session.setAttribute("user", dbUser);


		return dbUser;
	}
	
	@GetMapping(value = "/json/kakaoLogin")
    public ResponseEntity<String> kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
        try {
            String access_Token = kakaoService.getAccessToken(code);
            String kakaoId = kakaoService.getUserInfo(access_Token);

            if (kakaoId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kakao user information not found");
            }

            User user = userService.getUserByKakaoId(kakaoId);

            if (user == null) {
                // 카카오 사용자 정보가 없으면 회원가입 페이지로 리다이렉트
                session.setAttribute("kakaoId", kakaoId);
                redirectAttributes.addAttribute("kakaoId", kakaoId);
                return ResponseEntity.status(HttpStatus.FOUND).body("redirect:/user/addUser");
            }

            // 로그인 성공 처리
            session.setAttribute("user", user);
            return ResponseEntity.status(HttpStatus.FOUND).body("redirect:/index.jsp");
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}