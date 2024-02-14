package com.todostudy.myjob.global.security;

import com.todostudy.myjob.domain.member.service.AuthTokenService;
import com.todostudy.myjob.global.https.ReqData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomAuthenticationSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {
    private final ReqData req;
    private final AuthTokenService authTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String redirectUrlAfterSocialLogin = req.getCookieValue("redirectUrlAfterSocialLogin", "");

        if (req.isFrontUrl(redirectUrlAfterSocialLogin)) {
            String accessToken = authTokenService.genAccessToken(req.getMember());
            String refreshToken = req.getMember().getRefreshToken();

            req.destroySession();
            req.setCrossDomainCookie("accessToken", accessToken);
            req.setCrossDomainCookie("refreshToken", refreshToken);
            req.removeCookie("redirectUrlAfterSocialLogin");

            response.sendRedirect(redirectUrlAfterSocialLogin);
            return;
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }