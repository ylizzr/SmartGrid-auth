package com.energiainteligente.authservice.configuracion;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final DefaultOAuth2AuthorizationRequestResolver defaultResolver;

    public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo) {
        this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, "/oauth2/authorization");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest authRequest = defaultResolver.resolve(request);
        return customizeAuthorizationRequest(request, authRequest);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest authRequest = defaultResolver.resolve(request, clientRegistrationId);
        return customizeAuthorizationRequest(request, authRequest);
    }

    private OAuth2AuthorizationRequest customizeAuthorizationRequest(HttpServletRequest request, OAuth2AuthorizationRequest authRequest) {
        if (authRequest == null) return null;

        String rol = request.getParameter("rol");
        if (StringUtils.hasText(rol)) {
            Map<String, Object> extraParams = new HashMap<>(authRequest.getAttributes());
            extraParams.put("rol", rol);

            return OAuth2AuthorizationRequest.from(authRequest)
                    .attributes(extraParams)
                    .build();
        }
        return authRequest;
    }
}
