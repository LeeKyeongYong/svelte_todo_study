package com.todostudy.myjob.domain.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Tag(name="HomeController",description = "홈 컨트롤러, 특별한 기능은 없다.")
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    @Operation(summary = "메인화면을 보여주는 역할, 특별한 기능은 없다.")
    public String showMain(){
        return "백엔드 서버 입니다.";
    }
}
