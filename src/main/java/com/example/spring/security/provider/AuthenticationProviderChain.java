package com.example.spring.security.provider;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.Objects;

import static com.example.spring.security.common.ErrorConstants.PROVIDERS_NULL;

public class AuthenticationProviderChain implements InitializingBean, AuthenticationProvider {
    private List<AuthenticationProvider> providers;

    @Override
    public void afterPropertiesSet() throws Exception {
        Objects.requireNonNull(providers, PROVIDERS_NULL);
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        Authentication result = authentication;
        for (AuthenticationProvider authenticationProvider : providers) {
            if (authenticationProvider.supports(authentication.getClass())) {
                result = authenticationProvider.authenticate(result);
            }
        }
        return result;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setProviders(final List<AuthenticationProvider> providers) {
        this.providers = providers;
    }
}
