package com.todostudy.myjob.domain.member.controller;


import com.todostudy.myjob.global.https.ReqData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Tag(name ="MemberController",description="스웨거 로그인 용 로그인 폼, 카카오 로그인 기능 다양한 기능 제공")
public class MemberController {

    private final ReqData req;

    @GetMapping("/login")
    @ResponseBody
    @Operation(summary = "관리자 페이지에서 스웨거 로그인시에 ADMIN권한을 필요로함, 그것을 위한 로그인 폼 보여줌")
    public String login(){
        return """
                 <form method="POST">
                                    <div>
                                        <label>아이디</label>
                                        <input type="text" name="username">
                                    </div>
                                    <div>
                                        <label>비밀번호</label>
                                        <input type="password" name="password">
                                    </div>
                                    <div>
                                        <button type="submit">로그인</button>
                                    </div>
                                </form>
                """;
    }

    @GetMapping("/socialLogin/{providerTypeCode}")
    @Operation(summary = "프론트에서 소셜 로그인 후 원래 페이지로 돌아가기 위한 용도로 사용됨, redirectUrl 파라미터를 받아서 쿠키에 저장함")
    public String socialLogin(String redirectUrl, @PathVariable String prividerTypeCode){
        if(req.isFrontUrl(redirectUrl)){
            req.setCookie("redirectUrlAfterSocialLogin",redirectUrl,60*10);
        }
        return "redirect:/oauth2/authorization/"+prividerTypeCode;
    }

}
