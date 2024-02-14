package com.todostudy.myjob.global.initdata;

import com.todostudy.myjob.domain.member.service.MemberService;
import com.todostudy.myjob.global.app.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import com.todostudy.myjob.domain.member.entity.Member;
@Configuration
@Slf4j
@RequiredArgsConstructor
public class AllVersion {
    private final MemberService memberService;

    @Value("${custom.prod.members.system.password}")
    private String prodMemberSystemPassword;

    @Value("${custom.prod.members.admin.password}")
    private String prodMemberAdminPassword;

    @Bean
    @Order(2)
    public ApplicationRunner initAll() {
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                new File(AppConfig.getTempDirPath()).mkdirs();

                if (memberService.findByUsername("system").isPresent()) return;

                String memberSystemPassword = AppConfig.isProd() ? prodMemberSystemPassword : "1234";
                String memberAdminPassword = AppConfig.isProd() ? prodMemberAdminPassword : "1234";

                Member memberSystem = memberService.join("system", memberSystemPassword).getData();
                memberSystem.setRefreshToken("system");

                Member memberAdmin = memberService.join("admin", memberAdminPassword).getData();
                memberAdmin.setRefreshToken("admin");
            }
        };
    }
}
