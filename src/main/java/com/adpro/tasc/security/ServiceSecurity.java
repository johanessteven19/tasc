package com.adpro.tasc.security;

import com.adpro.tasc.security.filter.ApplicationUserDetailsService;
import com.adpro.tasc.security.filter.AuthFailHandler;
import com.adpro.tasc.security.filter.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServiceSecurity extends WebSecurityConfigurerAdapter {
    private ApplicationUserDetailsService userDetailsService;
    private AuthFailHandler authFailHandler;
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    public void setAuthFailHandler(AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }

    @Autowired
    public void setAuthSuccessHandler(AuthSuccessHandler authSuccessHandler) {
        this.authSuccessHandler = authSuccessHandler;
    }

    @Autowired
    public void setUserDetailsService(ApplicationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .formLogin()
                    .loginPage("/")
                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailHandler)
                    .and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/add-roles/**").hasRole("ADMIN")
                    .antMatchers("/home-student").hasRole("STUDENT")
                    .antMatchers("/home-admin").hasRole("ADMIN")
                    .antMatchers("/home-TA").hasRole("TEACHING_ASSISTANT")
                    .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return source;
    }
}