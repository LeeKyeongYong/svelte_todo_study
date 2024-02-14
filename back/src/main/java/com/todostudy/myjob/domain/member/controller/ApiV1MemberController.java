package com.todostudy.myjob.domain.member.controller;

import com.todostudy.myjob.domain.member.dto.MemberDto;
import com.todostudy.myjob.domain.member.record.LoginRequestBody;
import com.todostudy.myjob.domain.member.record.LoginResponseBody;
import com.todostudy.myjob.domain.member.record.MeResponseBody;
import com.todostudy.myjob.domain.member.service.MemberService;
import com.todostudy.myjob.global.https.ReqData;
import com.todostudy.myjob.global.https.RespData;
import com.todostudy.myjob.standard.base.Empty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping(value = "/api/v1/members", consumes  = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
@Tag(name="ApiV1MemberController",description="회원 CRUD 컨트롤러")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1MemberController {

    private final MemberService memberService;
    private final ReqData req;

    @PostMapping(value="/login")
    @Operation(summary = "로그인, accessToken, refreshToken 쿠키 생성됨")
    public RespData<LoginResponseBody> login(@Valid @RequestBody LoginRequestBody body){
        RespData<MemberService.AuthAndMakeTokensResponseBody> authAndMakeTokensRs = memberService.authAndMakeTokens(
                body.username,
                body.password
        );

        req.setCrossDomainCookie("refreshToken",authAndMakeTokensRs.getData().refreshToken());
        req.setCrossDomainCookie("accessToken",authAndMakeTokensRs.getData().accessToken());

        return authAndMakeTokensRs.newDataOf(
                new LoginResponseBody(
                        new MemberDto(authAndMakeTokensRs.getData().member())
                )
        );
    }

    @GetMapping(value ="/me",consumes = ALL_VALUE)
    @Operation(summary = "내 정보")
    @SecurityRequirement(name = "bearrerAuth")
    public RespData<MeResponseBody> getMe(){
        return RespData.of(
                new MeResponseBody(
                        new MemberDto(req.getMember())
                )
        );
    }

    @PostMapping(value = "/logout", consumes = ALL_VALUE)
    @Operation(summary = "로그아웃")
    public RespData<Empty> logout(){
        req.setLogout();

        return RespData.of("로그아웃 성공");
    }
}
