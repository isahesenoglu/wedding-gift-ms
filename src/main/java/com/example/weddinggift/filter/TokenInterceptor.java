package com.example.weddinggift.filter;

import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.model.User;
import com.example.weddinggift.service.CredentialService;
import com.example.weddinggift.service.impl.CredentialServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final CredentialServiceImpl credentialServiceImpl;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        // Token yoxlanışı: boş və ya mövcud deyil
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing Authorization token");
            return false;
        }

        // Tokeni doğrula və user obyektini əldə et
        Optional<User> user = credentialServiceImpl.validateTokenAndGetUser(token);

        // İstifadəçi obyektini lazımi yerlərdə istifadə üçün request atributuna əlavə et
        request.setAttribute("authenticatedUser", user);

        // Əgər hər şey qaydasındadırsa, sorğu davam etsin
        return true;
    }

}
