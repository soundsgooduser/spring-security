package com.example.spring.security.config;

import com.example.spring.security.filter.CustomBasicAuthenticationFilter;
import com.example.spring.security.provider.AuthenticationProviderChain;
import com.example.spring.security.provider.AuthenticationProviderServiceOne;
import com.example.spring.security.provider.AuthenticationProviderServiceTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAt(customBasicAuthenticationFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(authenticationProviderChain());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomBasicAuthenticationFilter customBasicAuthenticationFilter() throws Exception {
        final CustomBasicAuthenticationFilter filter = new CustomBasicAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public AuthenticationProviderChain authenticationProviderChain() {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(authenticationProviderServiceOne());
        providers.add(authenticationProviderServiceTwo());
        final AuthenticationProviderChain authenticationProviderChain = new AuthenticationProviderChain();
        authenticationProviderChain.setProviders(providers);
        return authenticationProviderChain;
    }

    @Bean
    public AuthenticationProvider authenticationProviderServiceOne() {
        return new AuthenticationProviderServiceOne();
    }

    @Bean
    public AuthenticationProvider authenticationProviderServiceTwo() {
        return new AuthenticationProviderServiceTwo();
    }
}
