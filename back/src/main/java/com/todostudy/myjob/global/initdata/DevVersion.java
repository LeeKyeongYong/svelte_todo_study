package com.todostudy.myjob.global.initdata;

import com.todostudy.myjob.global.app.AppConfig;
import com.todostudy.myjob.standard.base.UtZip;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class DevVersion {

    @Bean
    @Order(4)
    ApplicationRunner initDev(){
        return args ->{
            String backUrl = AppConfig.getSiteBackUrl();
            String cmd = "npx openapi-typescript "+backUrl+"/v3/api-docs/apiV1 -o ./front/src/lib/types/api/v1/schema.d.ts";
            UtZip.cmd.runAsync(cmd);
        };
    }
}
