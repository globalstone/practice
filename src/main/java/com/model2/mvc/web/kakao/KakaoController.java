//package com.model2.mvc.web.kakao;
//
//import com.model2.mvc.service.domain.Kakao;
//import com.model2.mvc.service.kakao.KakaoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//
//
//@Controller
//@RequestMapping("/kakao/*")
//public class KakaoController {
//
//    @Autowired
//    @Qualifier("kakaoServiceImpl")
//    private KakaoService kakaoService;
//
//    @RequestMapping(value="/login", method=RequestMethod.GET)
//    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model,
//                             HttpSession session) throws Exception{
//        String access_Token = kakaoService.getAccessToken(code);
//        Kakao userInfo = kakaoService.getUserInfo(access_Token);
//        System.out.println("#########" + code);
//        session.setAttribute("kakao",userInfo);
//        session.setAttribute("kakaoId", userInfo.getK_id());
//        System.out.println(userInfo);
//
//
//        return "redirect:/main.jsp";
//    }
//
//
//} 폐기